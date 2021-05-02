package com.devhub.campus.ui.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devhub.campus.ui.AppSurface
import com.devhub.campus.ui.Center
import com.devhub.campus.ui.auth.components.BigButton
import com.devhub.campus.ui.feed.data.posts
import com.devhub.campus.ui.theme.ThemedPreview
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.toPaddingValues
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

/* Todo: implement full screen design */

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FeedScreen(
    modifier: Modifier = Modifier
) {
        AppSurface(modifier) {
            Column(
                modifier = Modifier
                    .padding(LocalWindowInsets.current.systemBars.toPaddingValues(
                        top = true,
                        additionalTop = 8.dp
                    ))
            ) {
                Center(modifier = Modifier.weight(0.1f)) {
                    Text("Campus Feed")
                }
                Row(modifier = Modifier.weight(0.2f)) {
                    Text("Name and username")
                    Box(modifier = Modifier
                        .width(16.dp)
                        .background(Color.Cyan))
                }
                LazyRow(modifier = Modifier.weight(0.5f)) {
                    items(10) {
                        Card(
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Center(modifier = Modifier.padding(horizontal = 8.dp)) {
                                Text("Test Post: ${it.toString()}")
                            }
                        }
                    }
                }
                Center(
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(0.2f)) {
                    Button(onClick = { /*TODO*/ }) {
                        Text("New Post")
                    }
                }
            }
        }
}

@Preview(heightDp = 640, widthDp = 360)
@Composable
fun PreviewFeedScreen() {
    ThemedPreview() {
        FeedScreen()
    }
}

@Preview(heightDp = 640, widthDp = 360)
@Composable
fun PreviewFeedScreenDark() {
    ThemedPreview(true) {
        FeedScreen()
    }
}