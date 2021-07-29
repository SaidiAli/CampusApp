package com.devhub.campus.ui.feed

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.devhub.campus.app.models.Post
import com.devhub.campus.utils.FeedDataManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FeedsViewModel
@Inject constructor(
    dataManager: FeedDataManager
): ViewModel() {
    val posts: List<Post> = dataManager.posts
}