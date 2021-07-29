package com.devhub.campus.ui.feed

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.devhub.campus.app.models.Post
import com.devhub.campus.ui.AppSurface
import com.devhub.campus.ui.MainBottomNavigation
import com.devhub.campus.ui.feed.data.sampleList
import com.devhub.campus.ui.main.bottomNavSections
import com.devhub.campus.ui.theme.ThemedPreview
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.toPaddingValues

@ExperimentalComposeUiApi
@Composable
fun FeedScreen(
    contentPadding: PaddingValues,
    viewModel: FeedsViewModel,
    navigateToSinglePostScreen: (String) -> Unit
) {
    FeedBody(
        contentPadding = contentPadding,
        posts = viewModel.posts,
        vm = viewModel,
        goToSinglePost = navigateToSinglePostScreen
    )
}

@ExperimentalComposeUiApi
@Composable
fun FeedBody(
    contentPadding: PaddingValues,
    posts: List<Post>,
    vm: FeedsViewModel,
    goToSinglePost: (String) -> Unit
) {
    AppSurface {
        Column(modifier = Modifier.padding(paddingValues = contentPadding)) {
            BoxWithConstraints(modifier = Modifier.weight(0.8f)) {
                LazyRow {
                    items(posts.size) {
                        PostItem(
                            post = posts[it],
                            modifier = Modifier
                                .padding(8.dp)
                                .width(maxWidth.times(0.8f)),
                            goToSinglePost = goToSinglePost,
                            viewModel = vm
                        )
                    }
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@Preview(heightDp = 640, widthDp = 360)
@Composable
fun PreviewFeedScreen() {
    ThemedPreview() {
        // FeedBody(LocalWindowInsets.current.navigationBars.toPaddingValues(), sampleList, {})
    }
}

@ExperimentalComposeUiApi
@Preview(heightDp = 640, widthDp = 360)
@Composable
fun PreviewFeedScreenDark() {
    val navController = rememberNavController()

    ThemedPreview(true) {
        Scaffold(
            bottomBar = { MainBottomNavigation(
                navController = navController,
                sections = bottomNavSections
            ) },
        ) {
            // FeedBody(LocalWindowInsets.current.navigationBars.toPaddingValues(), sampleList, {})
        }
    }
}

@ExperimentalComposeUiApi
@Preview(heightDp = 640, widthDp = 360)
@Composable
fun PreviewFeedScreenDarkNoScaffold() {
    ThemedPreview(true) {
        // FeedBody(LocalWindowInsets.current.navigationBars.toPaddingValues(), sampleList, {})
    }
}