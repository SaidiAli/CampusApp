package com.devhub.campus.ui.feed

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.devhub.campus.app.models.Post
import com.devhub.campus.utils.FeedDataManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SinglePostViewModel
@Inject constructor(
    dataManager: FeedDataManager
): ViewModel() {
    private val posts: List<Post> = dataManager.posts

    fun getPost(id :String): Post? {
        return posts.find { post -> post.id == id }
    }

    var menuExpanded = mutableStateOf(false)
        private set

    fun toggleExpandState() {
        menuExpanded.value = true;
    }
}