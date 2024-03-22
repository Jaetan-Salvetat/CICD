package com.example.models.responses

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val message: String,
    val success: Boolean = false
)