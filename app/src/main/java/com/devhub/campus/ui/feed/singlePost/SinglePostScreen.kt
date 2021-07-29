package com.devhub.campus.ui.feed.singlePost

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.devhub.campus.ui.AppSurface
import com.devhub.campus.ui.feed.SinglePostViewModel
import com.google.accompanist.pager.ExperimentalPagerApi

object SinglePostScreenOpt {
    const val ARG = "postId"
    const val ROUTE = "singlePost/{postId}"

    fun arg(id: String) = "singlePost/$id"
}

@ExperimentalPagerApi
@Composable
fun SinglePostScreen(
    contentPadding: PaddingValues,
    viewModel: SinglePostViewModel,
    id: String
) {
    val post = viewModel.getPost(id)

    AppSurface {
        SinglePost(
            contentPadding = contentPadding,
            post = post,
            vm = viewModel
        )
    }
}
