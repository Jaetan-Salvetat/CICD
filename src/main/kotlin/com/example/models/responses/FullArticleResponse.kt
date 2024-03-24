package com.example.models.responses

import kotlinx.serialization.Serializable

@Serializable
data class FullArticleResponse(
    val id: Int,
    val title: String,
    val body: String
)
