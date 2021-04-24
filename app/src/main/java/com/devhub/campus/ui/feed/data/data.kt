package com.devhub.campus.ui.feed.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.devhub.campus.app.models.Post
import com.devhub.campus.app.models.UserModel
import com.google.type.DateTime
import java.time.format.DateTimeFormatter

val posts: List<Post> = listOf(
    Post(
        id = "dc523f0ed25c",
        text = "Lorem Ipsum doret lal lala",
        user = UserModel(name = "john")
    ),
    Post(
        id = "dc523f0ed253",
        text = "Lorem Ipsum doret lal lala",
        user = UserModel(name = "Peter")
    ),
    Post(
        id = "dc523f0ed251",
        text = "Lorem Ipsum doret lal lala",
        user = UserModel(name = "Jamal")
    )
)