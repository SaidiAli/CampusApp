package com.devhub.campus.ui.feed.data

import com.devhub.campus.app.models.Post
import com.devhub.campus.utils.Result

class PostsRepositoryImpl : PostsRepository {
    override suspend fun getPosts(): Result<List<Post>> {
        // TODO("get these from a firebase service")
        return Result.Success(posts)
    }

    override suspend fun getPost(): Result<Post> {
        // TODO("get this from a firebase service")
        return Result.Success(posts.first())
    }

    override suspend fun createPost(): Result<Post> {
        TODO("use a firebase service fi dis")
    }
}
