package com.example.andoridtask.model

data class ImageListItem (
    val alt_description: String,
    val asset_type: String,
    val blur_hash: String,
    val breadcrumbs: List<Any>,
    val color: String,
    val created_at: String,
    val current_user_collections: List<Any>,
    val description: String,
    val height: Int,
    val id: String,
    val liked_by_user: Boolean,
    val likes: Int,
    val promoted_at: String,
    val slug: String,
    val sponsorship: Any,
    val updated_at: String,
    val urls: Urls,
    val width: Int
)