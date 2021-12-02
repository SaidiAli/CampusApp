package com.devhub.campus.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.*
import com.devhub.campus.ui.auth.ui.theme.CampusTheme
import com.devhub.campus.utils.Constants.DEBUG_TAG
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import dagger.hilt.android.AndroidEntryPoint

import com.google.android.gms.tasks.Task

import androidx.annotation.NonNull
import androidx.navigation.NavController
import com.devhub.campus.MainActivity
import com.devhub.campus.app.models.UserModel
import com.devhub.campus.services.AuthManager
import com.devhub.campus.services.FirebaseAuthService
import com.devhub.campus.services.FirebaseToLocalUserMapper
import com.devhub.campus.services.FirestoreService
import com.devhub.campus.utils.*

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.*
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import javax.inject.Inject


@AndroidEntryPoint
class MainAuthActivity : ComponentActivity() {
    private val viewModel: MainAuthViewModel by viewModels()
    private lateinit var googleSignInClient: GoogleSignInClient

    @Inject
    lateinit var auth: FirebaseAuth

    @Inject
    lateinit var firestore: FirestoreService

    @Inject
    lateinit var authManager: AuthManager

    @Inject
    lateinit var firebaseMapper: FirebaseToLocalUserMapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(WEB_CLIENT_ID)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        setContent {
            CampusTheme {
                Scaffold {
                    val navController = rememberNavController()

                    NavHost(navController, startDestination = Screen.StartingScreen.route) {
                        composable(Screen.StartingScreen.route) {
                            StartingScreen(
                                viewModel = hiltViewModel<MainAuthViewModel>(),
                                goToLoginScreen = {
                                    navController.navigate(route = Screen.Auth.route)
                                },
                                goToRegistrationScreen = {
                                    navController.navigate(route = Screen.Auth.route)
                                },
                                goToFeedsScreen = {
                                    navController.navigate(route = Screen.Feed.route) {
                                        popUpTo(route = Screen.StartingScreen.route) {
                                            inclusive = true
                                        }
                                    }
                                }
                            )
                        }

                        composable(Screen.Auth.route) {
                            AuthScreen(
                                viewModel = hiltViewModel<MainAuthViewModel>(),
                                authenticate = { signInWithGoogle() }
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                // Google Sign In was successful, authenticate with Firebase
                if (task.isSuccessful) {

                    viewModel.updateLocalUser(
                        nomre = task.result?.displayName,
                        e = task.result?.email
                    )
                    firebaseAuthWithGoogle(task.result?.idToken!!)
                } else {
                    Log.d(DEBUG_TAG, "Google sign in task failed", task.exception)

                    Toast.makeText(this, "signin task failed, please try again", Toast.LENGTH_SHORT)
                        .show()
                }
            } catch (e: ApiException) {
                Log.d(DEBUG_TAG, "Google sign in failed with status code: " + e.statusCode, e)

                // Todo: format the error message according to the error code
                Toast.makeText(this, "failed to signin", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)

        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->

                viewModel.userExists.observe(this, EventObserver {
                    if(it) startMainActivity() else startSetupProfileActivity()
                })

                if (task.isSuccessful) {
                    Toast.makeText(
                        this,
                        "signed in successfully",
                        Toast.LENGTH_SHORT
                    ).show()

                    // check if user exists or new account
                    viewModel.checkIfUserExists(task.result?.user?.uid)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.d(DEBUG_TAG, "firebase signIn failed")

                    Toast.makeText(
                        this,
                        "SignIn failed, please check your connection",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun startSetupProfileActivity() {
        startActivity(Intent(this, SetupProfileActivity::class.java))
    }

    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CampusTheme {}
}