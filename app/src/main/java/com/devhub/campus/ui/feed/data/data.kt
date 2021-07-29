package com.devhub.campus.ui.feed.data

import com.devhub.campus.app.models.Media
import com.devhub.campus.app.models.Mime
import com.devhub.campus.app.models.Post
import com.devhub.campus.app.models.UserModel

val sampleList: List<Post> = listOf(
    Post(
        id = "dc523f0ed25c",
        text = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, " +
                "sed do eiusmod tempor #incididunt." +
                "Lorem ipsum dolor sit amet, consectetur adipisicing elit, " +
                "sed do eiusmod tempor #incididunt." +
                "Lorem ipsum dolor sit amet, consectetur adipisicing elit, " +
                "sed do eiusmod tempor #incididunt.",
        user = UserModel(
            name = "john",
            userName = "john256"
        ),
        mediaUrls = listOf(
            Media(
                mimeType = Mime.PNG,
                url = "https://bonstanainthecloud.herokuapp.com/images/1.jpg"
            )
        )
    ),
    Post(
        id = "dc523f0ed253",
        text = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, " +
                "sed do eiusmod tempor #incididunt.",
        user = UserModel(
            name = "Peter",
            userName = "pete234"
        ),
        mediaUrls = listOf(
            Media(
                mimeType = Mime.PNG,
                url = "https://bonstanainthecloud.herokuapp.com/images/1.jpg"
            ),
            Media(
                mimeType = Mime.PNG,
                url = "https://bonstanainthecloud.herokuapp.com/images/2.jpg"
            )
        )
    ),
    Post(
        id = "dc523f0ed251",
        text = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, " +
                "sed do eiusmod tempor #incididunt.",
        user = UserModel(
            name = "Jamal",
            userName = "jamal789"
        ),
        mediaUrls = listOf(
            Media(
                mimeType = Mime.PNG,
                url = "https://bonstanainthecloud.herokuapp.com/images/1.jpg"
            ),
            Media(
                mimeType = Mime.PNG,
                url = "https://bonstanainthecloud.herokuapp.com/images/1.jpg"
            ),
            Media(
                mimeType = Mime.PNG,
                url = "https://bonstanainthecloud.herokuapp.com/images/1.jpg"
            )
        )
    ),
    Post(
        id = "dc523f0ed251",
        text = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, " +
                "sed do eiusmod tempor #incididunt.",
        user = UserModel(
            name = "Jamal",
            userName = "jamal789"
        ),
        mediaUrls = listOf(
            Media(
                mimeType = Mime.PNG,
                url = "https://bonstanainthecloud.herokuapp.com/images/1.jpg"
            ),
            Media(
                mimeType = Mime.PNG,
                url = "https://bonstanainthecloud.herokuapp.com/images/2.jpg"
            ),
            Media(
                mimeType = Mime.PNG,
                url = "https://bonstanainthecloud.herokuapp.com/images/3.jpg"
            ),
            Media(
                mimeType = Mime.PNG,
                url = "https://bonstanainthecloud.herokuapp.com/images/4.jpg"
            )
        )
    ),
)