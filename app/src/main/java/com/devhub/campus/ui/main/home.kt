package com.devhub.campus.ui.main

import androidx.annotation.StringRes
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devhub.campus.R
import com.devhub.campus.ui.MainBottomNavigation
import com.devhub.campus.ui.discover.DiscoverScreen
import com.devhub.campus.ui.feed.FeedScreen
import com.devhub.campus.ui.messages.MessagesScreen

@Composable
fun Home() {
    val sections =
        listOf(MainSection.Feed, MainSection.Discover, MainSection.Messages)
    val navController: NavHostController = rememberNavController()

    Scaffold(
        bottomBar = { MainBottomNavigation(navController = navController, sections = sections) }
    ) {
            innerPadding ->
        val modifier = Modifier.padding(innerPadding)

        NavHost(navController = navController, startDestination = MainSection.Feed.route) {
            composable(MainSection.Feed.route) { FeedScreen(modifier) }
            composable(MainSection.Discover.route) { DiscoverScreen(modifier) }
            composable(MainSection.Messages.route) { MessagesScreen(modifier) }
        }

    }
}

/* Todo: import icons */

sealed class MainSection(val route: String, @StringRes val label: Int, val icon: ImageVector) {
    object Feed : MainSection("feed", R.string.feed, Icons.Outlined.Home)
    object Discover : MainSection("discover", R.string.discover, Icons.Outlined.AccountCircle)
    object Messages :
        MainSection("messages", R.string.dm, Icons.Outlined.Email)

    // object Chat : MainSection("chat", R.string.label_chat, Icons.Outlined.Chat)
}