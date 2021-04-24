package com.devhub.campus.ui.feed.data

import com.devhub.campus.app.models.Post
import com.devhub.campus.utils.Result

interface PostsRepository {
    // fetch all posts
    suspend fun getPosts(): Result<List<Post>>

    // fetch a single post
    suspend fun getPost(): Result<Post>

    // create a new post
    suspend fun createPost(): Result<Post>
}