package com.devhub.campus.ui.feed.singlePost

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.devhub.campus.app.models.*
import com.devhub.campus.ui.Center
import com.devhub.campus.ui.MainBottomNavigation
import com.devhub.campus.ui.MyIcon
import com.devhub.campus.ui.feed.SinglePostViewModel
import com.devhub.campus.ui.main.bottomNavSections
import com.devhub.campus.ui.theme.ThemedPreview
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

// todo: reply text input

@ExperimentalPagerApi
@Composable
fun SinglePost(
    contentPadding: PaddingValues,
    post: Post?,
    vm: SinglePostViewModel
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(paddingValues = contentPadding)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (post != null) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TopRow(
                    user = post.user,
                    vm = vm
                )
                ThePost(
                    post = post
                )
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    ActionRow(
                        likes = post.likes,
                        spreads = post.spreads,
                        replies = post.replies.size
                    )
                }
            }

            Replies(
                replies = post.replies
            )
        } else {
            Center {
                Text(text = "Error: Technical error", color = MaterialTheme.colors.error)
            }
        }
    }
}

@Composable
fun TopRow(
    user: UserModel,
    vm: SinglePostViewModel,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            BoxWithConstraints(
                modifier = Modifier.requiredSize(50.dp)
            ) {
                val url = user.profileImage

                if (url != null) {
                    Image(
                        painter = rememberCoilPainter(request = url),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(RoundedCornerShape(4.dp)),
                    )
                } else {
                    Surface(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color = MaterialTheme.colors.primary,
                    ) {}
                }
            }
            Column(modifier = Modifier) {
                user.name?.let { Text(text = it) }
                user.userName?.let { Text(text = it) }
            }
        }

        ActionIcon(
            icon = MyIcon.MoreVert,
            description = "options",
            modifier = Modifier,
            onClick = { /*Todo: handle share button click */ }
        )
    }
}

@Composable
fun Replies(
    replies: List<Reply> = emptyList()
) {
    replies.forEach {
        SingleReply(
            reply = it,
            count = replies.size
        )
    }
}

@Composable
fun SingleReply(
    reply: Reply,
    count: Int
) {
    val url = reply.who.profileImage
    val txt = reply.text

    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        BoxWithConstraints(
            modifier = Modifier
                .requiredSize(45.dp)
                .clip(RoundedCornerShape(4.dp))
        ) {
            if (url != null) {
                Image(
                    painter = rememberCoilPainter(request = url),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(2.dp)
                )
            } else {
                Surface(
                    modifier = Modifier.size(45.dp),
                    color = MaterialTheme.colors.primary
                ) {}
            }
        }
        Column {
            if (txt != null) {
                Text(
                    text = txt,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            } else {
                Text(
                    text = "",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                ActionRow(
                    likes = reply.likes,
                    spreads = reply.spreads,
                    replies = count
                )
            }
        }
    }
}

@Composable
fun ActionRow(
    likes: List<Like>,
    spreads: Int,
    replies: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            ActionIcon(
                icon = MyIcon.FavoriteBorder,
                description = "like",
                onClick = { /*Todo: handle like button click */ }
            )
            Text(text = likes.size.toString())
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            ActionIcon(
                icon = MyIcon.QuestionAnswer,
                description = "comment",
                onClick = { /*Todo: handle reply button click */ }
            )
            Text(text = replies.toString())
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            ActionIcon(
                icon = MyIcon.Campaign,
                description = "spread",
                onClick = { /*Todo: handle spreads button click */ }
            )
            Text(text = spreads.toString())
        }
        ActionIcon(
            icon = MyIcon.Share,
            description = "share",
            onClick = { /*Todo: handle share button click */ }
        )
    }
}

@ExperimentalPagerApi
@Composable
fun ThePost(
    post: Post?
) {
    Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        if (post != null) {
            Text(
                text = post.text ?: "",
                modifier = Modifier.padding(vertical = 8.dp)
            )

            if(post.mediaUrls.isNotEmpty()) {
                ImageCarousel(
                    images = post.mediaUrls
                )
            }
        }
    }
}

// Todo: Consider Using Android ViewPager for the carousel

@ExperimentalPagerApi
@Composable
fun ImageCarousel(
    images: List<Media> = emptyList()
) {
    val pagerState = rememberPagerState(
        pageCount = images.size,
        initialPageOffset = 0.8f
    )

    BoxWithConstraints(
        modifier = Modifier
            .requiredHeight(250.dp)
            .fillMaxWidth()
    ) {
        HorizontalPager(state = pagerState) {
            val pageIndex: Int = currentPage
            Image(
                painter = rememberCoilPainter(request = images.elementAt(pageIndex).url),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(2.dp)
                    .clip(RoundedCornerShape(4.dp)),
            )
        }
    }
}

@Composable
private fun ActionIcon(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    description: String? = null,
    tint: Color = MaterialTheme.colors.primary,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = icon,
            contentDescription = description,
            tint = tint,
            modifier = modifier.size(20.dp)
        )
    }
}

@ExperimentalComposeUiApi
@Preview(heightDp = 640, widthDp = 360)
@Composable
fun PreviewFeedScreenDark() {

    ThemedPreview(true) {
        Scaffold(
            bottomBar = {
                MainBottomNavigation(
                    navController = rememberNavController(),
                    sections = bottomNavSections
                )
            },
        ) {
            // SinglePost(it)
        }
    }
}