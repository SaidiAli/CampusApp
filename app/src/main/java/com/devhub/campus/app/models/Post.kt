package com.devhub.campus.app.models

data class Post(
    val id: String,
    val text: String? = null,
    val user: UserModel,
    val likes: List<Like> = emptyList(),
    val spreads: Int = 0,
    val mediaUrls: List<Media> = emptyList(),
    val tags: List<Tag> = emptyList(),
    val attachments: List<Attachment> = emptyList(),
    val replies: List<Reply> = emptyList(),
)

data class Reply(
    val id: String,
    val text: String? = null,
    val mediaUrls: List<Media>  = emptyList(),
    val attachments: List<Attachment> = emptyList(),
    val likes: List<Like> = emptyList(),
    val spreads: Int = 0
)

data class Attachment(
    val type: AttachmentType,
)

enum class AttachmentType {
    LINK,
    DOC,
}

data class Tag(
    val title: String,
    val usage: Int
)

data class Like(
    val who: UserModel
)

data class Media(
    val url: String,
    val mimeType: Mime
)

enum class Mime {
    JPEG,
    PNG,
    MP4
}
