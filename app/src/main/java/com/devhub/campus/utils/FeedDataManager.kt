package com.devhub.campus.utils

import com.devhub.campus.app.models.*
import javax.inject.Inject
import kotlin.random.Random

class FeedDataManager @Inject constructor() {
    val posts by lazy {
        listOf(
            Post(
                id = "dc523f0ed25c",
                text = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, " +
                        "sed do eiusmod tempor #incididunt." +
                        "Lorem ipsum dolor sit amet, consectetur adipisicing elit, " +
                        "sed do eiusmod tempor #incididunt." +
                        "Lorem ipsum dolor sit amet, consectetur adipisicing elit, " +
                        "sed do eiusmod tempor #incididunt.",
                user = users.elementAt(0),
                spreads = 100,
                likes = users.map { Like(who = it) }.take(2),
                replies = listOf(
                    Reply(
                        id = "dc523f0ed25b",
                        text = "some random text as a reply",
                        spreads = 12,
                        who = users.random(),
                        likes = listOf(
                            Like(
                                who = users.random()
                            )
                        )
                    )
                )
            ),
            Post(
                id = "dc523f0ed25c",
                text = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, " +
                        "sed do eiusmod tempor #incididunt." +
                        "Lorem ipsum dolor sit amet, consectetur adipisicing elit, " +
                        "sed do eiusmod tempor #incididunt." +
                        "Lorem ipsum dolor sit amet, consectetur adipisicing elit, " +
                        "sed do eiusmod tempor #incididunt.",
                user = users.elementAt(0),
                mediaUrls = listOf(
                    Media(
                        mimeType = Mime.PNG,
                        url = "https://bonstanainthecloud.herokuapp.com/images/big/5.jpg"
                    )
                ),
                spreads = 100,
                likes = users.map { Like(who = it) }.take(2),
                replies = listOf(
                    Reply(
                        id = "dc523f0ed25b",
                        text = "some random text as a reply",
                        spreads = 12,
                        who = users.random(),
                        likes = listOf(
                            Like(
                                who = users.random()
                            )
                        )
                    )
                )
            ),
            Post(
                id = "dc523f0ed253",
                text = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, " +
                        "sed do eiusmod tempor #incididunt.",
                user = users.random(),
                mediaUrls = listOf(
                    Media(
                        mimeType = Mime.PNG,
                        url = "https://bonstanainthecloud.herokuapp.com/images/big/1.jpg"
                    ),
                    Media(
                        mimeType = Mime.PNG,
                        url = "https://bonstanainthecloud.herokuapp.com/images/big/2.jpg"
                    )
                ),
                spreads = 105,
                likes = users.map { Like(who = it) }.take(5),
                replies = listOf(
                    Reply(
                        id = "dc523f0ed25b",
                        text = "some random text as a reply",
                        spreads = 12,
                        who = users.random(),
                        likes = users.map { Like(who = it) }.take(3)
                    ),
                    Reply(
                        id = "dc523f0ed24b",
                        text = "some random text as a reply",
                        spreads = 12,
                        who = users.random(),
                        likes = users.map { Like(who = it) }.take(3)
                    )
                )
            ),
            Post(
                id = "dc523f0ed251",
                text = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, " +
                        "sed do eiusmod tempor #incididunt.",
                user = users.random(),
                mediaUrls = listOf(
                    Media(
                        mimeType = Mime.PNG,
                        url = "https://bonstanainthecloud.herokuapp.com/images/big/1.jpg"
                    ),
                    Media(
                        mimeType = Mime.PNG,
                        url = "https://bonstanainthecloud.herokuapp.com/images/big/2.jpg"
                    ),
                    Media(
                        mimeType = Mime.PNG,
                        url = "https://bonstanainthecloud.herokuapp.com/images/big/3.jpg"
                    ),
                ),
                spreads = 305,
                likes = users.map { Like(who = it) }.take(3),
                replies = listOf(
                    Reply(
                        id = "dc523f0ed25h",
                        text = "some random text as a reply",
                        spreads = 12,
                        who = users.random(),
                        likes = users.map { Like(who = it) }.take(3)
                    ),
                    Reply(
                        id = "dc523f0ed24d",
                        text = "some random text as a reply",
                        spreads = 12,
                        who = users.random(),
                        likes = users.map { Like(who = it) }.take(3)
                    )
                )
            ),
            Post(
                id = "dc523f0ed251",
                text = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, " +
                        "sed do eiusmod tempor #incididunt.",
                user = users.random(),
                mediaUrls = listOf(
                    Media(
                        mimeType = Mime.PNG,
                        url = "https://bonstanainthecloud.herokuapp.com/images/big/1.jpg"
                    ),
                    Media(
                        mimeType = Mime.PNG,
                        url = "https://bonstanainthecloud.herokuapp.com/images/big/2.jpg"
                    ),
                    Media(
                        mimeType = Mime.PNG,
                        url = "https://bonstanainthecloud.herokuapp.com/images/big/3.jpg"
                    ),
                    Media(
                        mimeType = Mime.PNG,
                        url = "https://bonstanainthecloud.herokuapp.com/images/big/4.jpg"
                    ),
                ),
                spreads = 534,
                likes = users.map { Like(who = it) }.take(5),
                replies = listOf(
                    Reply(
                        id = "dc523f06d25b",
                        text = "some random text as a reply",
                        spreads = 12,
                        who = users.random(),
                        likes = users.map { Like(who = it) }.take(3)
                    ),
                    Reply(
                        id = "dc523f03d24b",
                        text = "some random text as a reply",
                        spreads = 12,
                        who = users.random(),
                        likes = users.map { Like(who = it) }.take(3)
                    ),
                    Reply(
                        id = "dc523f06d25b",
                        text = "some random text as a reply",
                        spreads = 12,
                        who = users.random(),
                        likes = users.map { Like(who = it) }.take(3)
                    ),
                    Reply(
                        id = "dc523f03d24b",
                        text = "some random text as a reply",
                        spreads = 12,
                        who = users.random(),
                        likes = users.map { Like(who = it) }.take(3)
                    )
                )
            ),
        )
    }

    var users: List<UserModel> = listOf(
        UserModel(
            name = "john",
            userName = "john256",
            profileImage = "https://bonstanainthecloud.herokuapp.com/images/small/face2.jpg"
        ),
        UserModel(
            name = "Peter",
            userName = "pete234",
            // profileImage = "https://bonstanainthecloud.herokuapp.com/images/small/face1.jpg"
        ),
        UserModel(
            name = "Jamal",
            userName = "jamal789",
            profileImage = "https://bonstanainthecloud.herokuapp.com/images/small/face3.jpg"
        ),
        UserModel(
            name = "Jamal",
            userName = "jamal789",
            profileImage = "https://bonstanainthecloud.herokuapp.com/images/small/face4.jpg"
        ),
        UserModel(
            name = "john",
            userName = "john256",
            profileImage = "https://bonstanainthecloud.herokuapp.com/images/small/face2.jpg"
        ),
        UserModel(
            name = "Peter",
            userName = "pete234",
            // profileImage = "https://bonstanainthecloud.herokuapp.com/images/small/face1.jpg"
        ),
        UserModel(
            name = "Jamal",
            userName = "jamal789",
            profileImage = "https://bonstanainthecloud.herokuapp.com/images/small/face3.jpg"
        ),
        UserModel(
            name = "Jamal",
            userName = "jamal789",
            profileImage = "https://bonstanainthecloud.herokuapp.com/images/small/face4.jpg"
        ),
        UserModel(
            name = "john",
            userName = "john256",
            profileImage = "https://bonstanainthecloud.herokuapp.com/images/small/face2.jpg"
        ),
        UserModel(
            name = "Peter",
            userName = "pete234",
            // profileImage = "https://bonstanainthecloud.herokuapp.com/images/small/face1.jpg"
        ),
        UserModel(
            name = "Jamal",
            userName = "jamal789",
            profileImage = "https://bonstanainthecloud.herokuapp.com/images/small/face3.jpg"
        ),
        UserModel(
            name = "Jamal",
            userName = "jamal789",
            profileImage = "https://bonstanainthecloud.herokuapp.com/images/small/face4.jpg"
        )
    )

    private fun randomImage(): String {
        return imageUrls[Random.nextInt(imageUrls.size)]
    }

    var imageUrls: List<String> = listOf(
        "https://bonstanainthecloud.herokuapp.com/images/big/1.jpg",
        "https://bonstanainthecloud.herokuapp.com/images/big/2.jpg",
        "https://bonstanainthecloud.herokuapp.com/images/big/3.jpg",
        "https://bonstanainthecloud.herokuapp.com/images/big/4.jpg",
        "https://bonstanainthecloud.herokuapp.com/images/big/5.jpg",
        "https://bonstanainthecloud.herokuapp.com/images/big/6.jpg",
    )
}