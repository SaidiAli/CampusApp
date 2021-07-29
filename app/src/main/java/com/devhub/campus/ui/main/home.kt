package com.devhub.campus.ui.main

import androidx.annotation.StringRes
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devhub.campus.R
import com.devhub.campus.ui.MainAppBar
import com.devhub.campus.ui.MainBottomNavigation
import com.devhub.campus.ui.discover.DiscoverScreen
import com.devhub.campus.ui.feed.*
import com.devhub.campus.ui.feed.singlePost.SinglePostScreen
import com.devhub.campus.ui.feed.singlePost.SinglePostScreenOpt
import com.devhub.campus.ui.messages.MessagesScreen
import com.devhub.campus.ui.new.NewPost
import com.devhub.campus.ui.profile.ProfileScreen
import com.devhub.campus.ui.profile.ProfileViewModel
import com.google.accompanist.pager.ExperimentalPagerApi

val bottomNavSections =
    listOf(
        MainSection.Feed,
        MainSection.Discover,
        MainSection.NewPost,
        MainSection.Messages,
        MainSection.Profile
    )

@ExperimentalPagerApi
@ExperimentalComposeUiApi
@Composable
fun Home() {
    val navController: NavHostController = rememberNavController()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            MainBottomNavigation(
                navController = navController,
                sections = bottomNavSections
            )
        },
        topBar = {
            MainAppBar()
        },
        content = { innerPadding ->

            NavHost(
                navController = navController,
                startDestination = MainSection.Feed.route
            ) {
                composable(MainSection.Feed.route) {
                    FeedScreen(
                        contentPadding = innerPadding,
                        viewModel = hiltViewModel<FeedsViewModel>()
                    ) { route ->
                        navController.navigate(route) {
                            launchSingleTop = true
                        }
                    }
                }
                composable(MainSection.Discover.route) {
                    DiscoverScreen(contentPadding = innerPadding)
                }
                composable(MainSection.NewPost.route) {
                    NewPost(contentPadding = innerPadding)
                }
                composable(MainSection.Messages.route) {
                    MessagesScreen(contentPadding = innerPadding)
                }
                composable(MainSection.Profile.route) {
                    ProfileScreen(viewModel = hiltViewModel<ProfileViewModel>())
                }
                composable(SinglePostScreenOpt.ROUTE) {
                    SinglePostScreen(
                        contentPadding = innerPadding,
                        viewModel = hiltViewModel<SinglePostViewModel>(),
                        id = it.arguments?.getString(SinglePostScreenOpt.ARG).orEmpty()
                    )
                }
            }
        }
    )
}

sealed class MainSection(val route: String, @StringRes val label: Int, val icon: ImageVector) {
    object Feed : MainSection("feed", R.string.feed, Icons.Outlined.Home)
    object Discover : MainSection("discover", R.string.discover, Icons.Outlined.Whatshot)
    object NewPost : MainSection("new", R.string.newPost, Icons.Outlined.Add)
    object Messages :
        MainSection("messages", R.string.dm, Icons.Outlined.Email)

    object Profile : MainSection("profile", R.string.profile, Icons.Outlined.AccountCircle)
}