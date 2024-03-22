package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class FullArticleRequest(
    val title: String,
    val body: String
)
