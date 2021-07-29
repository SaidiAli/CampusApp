package com.devhub.campus.ui.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.outlined.VideoCameraFront
import androidx.compose.material.icons.outlined.Videocam
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.navigation.compose.rememberNavController
import com.devhub.campus.ui.Center
import com.devhub.campus.ui.MainBottomNavigation
import com.devhub.campus.ui.MyIcon
import com.devhub.campus.ui.main.bottomNavSections
import com.devhub.campus.ui.theme.ThemedPreview

@Composable
fun ProfileScreenBody(
    modifier: Modifier,
    state: ScrollState,
    vm: ProfileViewModel
) {
    BoxWithConstraints(
        modifier = Modifier.verticalScroll(state)
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            ProfileImage(
                modifier = Modifier
            )
            NameRow(
                modifier = Modifier,
            )
            FollowersRow(
                modifier = Modifier
            )
            Bio(
                modifier = Modifier
            )
            InfoColumn(
                modifier = Modifier
            )
            EventsColumn(
                modifier = Modifier
            )
        }
    }
}

@Composable
fun EventsColumn(
    modifier: Modifier = Modifier
) {
    Center{
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = "Events")

            EventCard(modifier = Modifier)
            EventCard(modifier = Modifier)
            EventCard(modifier = Modifier)
        }
    }
}

@Composable
fun EventCard(modifier: Modifier) {
    Surface(
        modifier = Modifier.size(width = 200.dp, height = 100.dp),
        shape = RoundedCornerShape(size = 4.dp),
        color = MaterialTheme.colors.primary
    ) {}
}

@Composable
fun InfoColumn(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth().padding(8.dp)
    ) {
        Column(modifier = Modifier) {
            Text(
                text = "Campus"
            )
            Text(text = "Makerere University, MUK")
            Text(
                text = "Leadership"
            )
            Text(text = "Guild President")
        }
    }
}

@Composable
fun Bio(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.padding(8.dp),
    ) {
        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipisicing elit," +
                    " sed do eiusmod tempor #incididunt."
        )
    }
}

@Composable
fun FollowersRow(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        NumberCard(modifier = Modifier, num = "10", text = "followers")
        NumberCard(modifier = Modifier, num = "10", text = "posts")
        NumberCard(modifier = Modifier, num = "10", text = "photos")
    }
}

@Composable
fun NumberCard(
    modifier: Modifier.Companion,
    num: String,
    text: String
) {
    Surface(
        modifier = modifier.size(100.dp),
        shape = RoundedCornerShape(size = 4.dp),
    ) {
        Center {
            Column(modifier = Modifier) {
                Text(
                    text = "${num}k",
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = text
                )
            }
        }
    }
}

@Composable
fun NameRow(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text("Name", style = MaterialTheme.typography.h2)
            Text("@username", style = MaterialTheme.typography.body1)
        }
        Box {
            IconButton(onClick = { /*TODO: Implement Go Live Button*/ }) {
                Icon(
                    imageVector = MyIcon.Videocam,
                    contentDescription = "go live button"
                )
            }
            ProfileOrFollowButton(modifier = Modifier)
        }
    }
}

@Composable
fun ProfileOrFollowButton(modifier: Modifier) {
    OutlinedButton(
        modifier = modifier,
        shape = RoundedCornerShape(size = 16.dp),
        border = BorderStroke(width = 2.dp, color = MaterialTheme.colors.primary),
        onClick = { /*TODO: Implement edit profile/follow button*/ }
    ) {
        Text(
            text = "Edit Profile",
            color = MaterialTheme.colors.primary
        )
    }
}

@Composable
fun ProfileImage(
    modifier: Modifier = Modifier
) {
    Center {
        /*CoilImage(
            data = "",
            contentDescription = null,
            modifier = Modifier
                .padding(2.dp)
                .clip(RoundedCornerShape(4.dp)),
            contentScale = ContentScale.Crop
        )*/

        Surface(
            modifier = Modifier.size(200.dp),
            shape = RoundedCornerShape(size = 4.dp),
            color = MaterialTheme.colors.primary
        ) {}
    }
}

private fun decoupledConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val img = createRefFor("img")
        val nameRow = createRefFor("nameRow")
        val followersRow = createRefFor("followersRow")
        val bio = createRefFor("bio")
        val infoColumn = createRefFor("infoColumn")
        val eventsColumn = createRefFor("eventsColumn")

        constrain(img) {
            top.linkTo(parent.top, margin)
            start.linkTo(parent.start, margin)
            end.linkTo(parent.end, margin)
            bottom.linkTo(nameRow.top, margin)
        }
        constrain(nameRow) {
            top.linkTo(img.bottom, margin)
            start.linkTo(parent.start, margin)
            end.linkTo(parent.end, margin)
            bottom.linkTo(followersRow.top, margin)
        }
        constrain(followersRow) {
            top.linkTo(nameRow.bottom, margin)
            start.linkTo(parent.start, margin)
            end.linkTo(parent.end, margin)
            bottom.linkTo(bio.top, margin)
        }
        constrain(bio) {
            top.linkTo(followersRow.bottom, margin)
            start.linkTo(parent.start, margin)
            end.linkTo(parent.end, margin)
            bottom.linkTo(infoColumn.top, margin)
        }
        constrain(infoColumn) {
            top.linkTo(bio.bottom, margin)
            start.linkTo(parent.start, margin)
            end.linkTo(parent.end, margin)
            bottom.linkTo(eventsColumn.top, margin)
        }
        constrain(eventsColumn) {
            top.linkTo(infoColumn.bottom, margin)
            // start.linkTo(parent.start, margin)
            // end.linkTo(parent.end, margin)
        }
    }
}

// preview screens

@Preview(heightDp = 640, widthDp = 360)
@Composable
fun PreviewProfileScreen() {
    ThemedPreview(true) {
        Scaffold(
            bottomBar = {
                MainBottomNavigation(
                    navController = rememberNavController(),
                    sections = bottomNavSections
                )
            },
        ) {
            // ProfileScreenBody(modifier = Modifier, state = rememberScrollState(),)
        }
    }
}