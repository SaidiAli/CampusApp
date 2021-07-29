package com.devhub.campus.ui.feed

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintSet
import com.devhub.campus.app.models.Media
import com.devhub.campus.app.models.Post
import com.devhub.campus.app.models.UserModel
import com.devhub.campus.ui.Center
import com.devhub.campus.ui.MyIcon
import com.devhub.campus.ui.feed.singlePost.SinglePostScreenOpt
import com.devhub.campus.ui.theme.BigName
import com.devhub.campus.ui.theme.ThemedPreview
import com.google.accompanist.coil.rememberCoilPainter

@ExperimentalComposeUiApi
@Composable
fun PostItem(
    post: Post,
    modifier: Modifier,
    goToSinglePost: (String) -> Unit,
    viewModel: FeedsViewModel
) {
    Column(modifier = modifier) {
        PostOwner(
            data = post.user,
            modifier = Modifier
                .weight(0.2f)
                .padding(vertical = 8.dp)
        )
        ConstrainedPostContent(
            modifier = Modifier.weight(0.3f),
            post = post,
            vm = viewModel,
            goToSinglePost = goToSinglePost,
        )
    }
}

@ExperimentalComposeUiApi
@Composable
fun ConstrainedPostContent(
    modifier: Modifier,
    post: Post,
    vm: FeedsViewModel,
    goToSinglePost: (String) -> Unit
) {
    Card{
        val constraints = decoupledConstraints(8.dp)

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            PostTopRow(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth(),
                vm = vm
            )
            PostContent(
                modifier = Modifier,
                post = post,
                goToSinglePost = goToSinglePost
            )
            PostActionRow(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth(),
                post = post
            )
        }
    }
}

@ExperimentalComposeUiApi
private fun decoupledConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val topRow = createRefFor("topRow")
        val content = createRefFor("content")
        val actionRow = createRefFor("actionRow")

        constrain(topRow) {
            top.linkTo(parent.top, margin)
        }
        constrain(content) {
            top.linkTo(topRow.bottom, margin)
        }
        constrain(actionRow) {
            bottom.linkTo(parent.bottom, margin)
        }
    }
}

// todo: implement the options bottomSheet
@Composable
fun PostTopRow(
    modifier: Modifier,
    vm: FeedsViewModel,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                "1h ago",
                modifier = Modifier
            )
        }
        ActionIcon (
            icon = MyIcon.MoreVert,
            description = "options",
            modifier = Modifier,
            onClick = {}
        )
    }
}

// todo: remove the ripple effect (hint: Modifier.indication)
@Composable
fun PostContent(
    modifier: Modifier = Modifier,
    post: Post,
    goToSinglePost: (String) -> Unit
) {
    BoxWithConstraints(
        modifier = modifier
            .clickable{ goToSinglePost(SinglePostScreenOpt.arg(post.id)) }
    ) {
        Column {
            Text(
                post.text ?: "",
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .padding(8.dp)
            )
            PostImageViewer(
                modifier = Modifier
                    .padding(8.dp),
                images = post.mediaUrls
            )
        }
    }
}

// Todo: Image caching
@Composable
fun PostImageViewer(
    modifier: Modifier = Modifier,
    images: List<Media> = emptyList(),
) {
    BoxWithConstraints(
        modifier = modifier
            .requiredHeight(250.dp)
            .fillMaxWidth()
    ) {
        when(images.size) {
            1 -> SingleImageLayout(
                images = images,
                maxWidth = maxWidth,
                maxHeight = maxHeight,
            )
            2 -> DoubleImagesLayout(
                images = images,
                maxWidth = maxWidth,
                maxHeight = maxHeight,
            )
            3 -> TripleImagesLayout(
                images = images,
                maxWidth = maxWidth,
                maxHeight = maxHeight,
            )
            4 -> FourImagesLayout(
                images = images,
                maxWidth = maxWidth,
                maxHeight = maxHeight,
            )
        }
    }
}

@Composable
fun SingleImageLayout(
    modifier: Modifier = Modifier,
    images: List<Media>,
    maxWidth: Dp,
    maxHeight: Dp
) {
    Center {
        PostImage(
            modifier = modifier
                .requiredHeight(maxHeight)
                .requiredWidth(maxWidth),
            url = images.first().url,
        )
    }
}

@Composable
fun DoubleImagesLayout(
    modifier: Modifier = Modifier,
    images: List<Media>,
    maxWidth: Dp,
    maxHeight: Dp
) {
    Center {
        Row {
            PostImage(
                url = images.elementAt(0).url,
                modifier = modifier
                    .requiredWidth(maxWidth / 2)
                    .requiredHeight(maxHeight)
                    .padding(end = .5.dp),
                scale = ContentScale.Crop
            )
            PostImage(
                url = images.elementAt(1).url,
                modifier = modifier
                    .requiredWidth(maxWidth / 2)
                    .requiredHeight(maxHeight)
                    .padding(start = .5.dp),
                scale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun TripleImagesLayout(
    modifier: Modifier = Modifier,
    images: List<Media>,
    maxWidth: Dp,
    maxHeight: Dp
) {
    Center{
        Column {
            PostImage(
                modifier = modifier
                    .requiredWidth(maxWidth)
                    .requiredHeight(maxHeight / 2)
                    .padding(bottom = .5.dp),
                url = images.elementAt(0).url,
            )
            Row {
                PostImage(
                    url = images.elementAt(1).url,
                    modifier = modifier
                        .requiredWidth(maxWidth / 2)
                        .requiredHeight(maxHeight / 2)
                        .padding(end = .5.dp),
                )
                PostImage(
                    url = images.elementAt(2).url,
                    modifier = modifier
                        .requiredWidth(maxWidth / 2)
                        .requiredHeight(maxHeight / 2),
                )
            }
        }
    }
}

@Composable
fun FourImagesLayout(
    modifier: Modifier = Modifier,
    images: List<Media>,
    maxWidth: Dp,
    maxHeight: Dp
) {
    Box(
        modifier = modifier
    ) {
        Column {
            Row {
                PostImage(
                    url = images.elementAt(0).url,
                    modifier = modifier
                        .requiredWidth(maxWidth / 2)
                        .requiredHeight(maxHeight / 2)
                        .padding(bottom = .5.dp, end = .5.dp),
                )
                PostImage(
                    url = images.elementAt(1).url,
                    modifier = modifier
                        .requiredWidth(maxWidth / 2)
                        .requiredHeight(maxHeight / 2)
                        .padding(bottom = .5.dp, ),
                )
            }
            Row {
                PostImage(
                    url = images.elementAt(2).url,
                    modifier = modifier
                        .requiredWidth(maxWidth / 2)
                        .requiredHeight(maxHeight / 2),
                )
                PostImage(
                    url = images.elementAt(3).url,
                    modifier = modifier
                        .requiredWidth(maxWidth / 2)
                        .requiredHeight(maxHeight / 2)
                        .padding(start = .5.dp),
                )
            }
        }
    }
}

@Composable
private fun PostImage(
    modifier: Modifier = Modifier,
    url: String,
    scale: ContentScale = ContentScale.Crop
) {
    Box(modifier = modifier) {
        Image(
            painter = rememberCoilPainter(request = url),
            contentDescription = null,
            contentScale = scale,
            modifier = Modifier
                .padding(2.dp)
                .clip(RoundedCornerShape(4.dp)),
        )
    }
}

// todo: when someone touches this, it takes them to the profile page

// todo: design a profile page
@Composable
fun PostOwner(
    data: UserModel,
    modifier: Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            data.name?.let {
                Text(
                    it,
                    style = BigName,
                    color = if (MaterialTheme.colors.isLight) Color.Black else Color.White,
                )
            }
            Text(
                userNameEdit(data.userName as String),
                style = MaterialTheme.typography.subtitle1,
                color = if (MaterialTheme.colors.isLight) Color.Black else Color.White,
            )
        }
        ProfileImageContainer(url = data.profileImage)
    }
}

@Composable
fun ProfileImageContainer(url: String?) {
    if(url != null) {
        BoxWithConstraints(
            modifier = Modifier.size(50.dp)
        ) {
            Image(
                painter = rememberCoilPainter(request = url),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(2.dp)
                    .clip(RoundedCornerShape(4.dp)),
            )
        }
    } else {
        Surface(
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(4.dp)),
            color = MaterialTheme.colors.primary,
        ) {}
    }
}

// todo: all of these should be IconButtons and should be dynamic counting
@Composable
fun PostActionRow(
    modifier: Modifier = Modifier,
    post: Post
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            ActionIcon(
                icon = MyIcon.FavoriteBorder,
                description = "like",
                onClick = { /* Todo: handle like button click */ }
            )
            Text(text = post.likes.size.toString())
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            ActionIcon(
                icon = MyIcon.QuestionAnswer,
                description = "comment",
                onClick = { /* Todo: handle reply button click */ }
            )
            Text(text = post.replies.size.toString())
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            ActionIcon(
                icon = MyIcon.Campaign,
                description = "spread",
                onClick = { /* Todo: handle spread button click */ }
            )
            Text(text = post.spreads.toString())
        }
        ActionIcon(
            icon = MyIcon.Share,
            description = "share",
            onClick = { /* Todo: handle share button click */ }
        )
    }
}

fun userNameEdit(text: String): String {
    return "@$text"
}

@Preview(heightDp = 640, widthDp = 360)
@Composable
fun PreviewF() {
    ThemedPreview(true) {
        Center {
            Column{
                Surface (
                    modifier = Modifier.size(50.dp),
                    color = Color.Cyan
                ) {}
                Row {
                    Surface(
                        modifier = Modifier.size(50.dp),
                        color = Color.Cyan
                    ) {}
                    Surface(
                        modifier = Modifier
                            .size(50.dp)
                            .padding(end = 8.dp),
                        color = Color.Cyan
                    ) {}
                    Surface(
                        modifier = Modifier.size(50.dp),
                        color = Color.Cyan
                    ) {}
                }
                Surface(
                    modifier = Modifier.size(50.dp),
                    color = Color.Cyan
                ) {}
            }
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
