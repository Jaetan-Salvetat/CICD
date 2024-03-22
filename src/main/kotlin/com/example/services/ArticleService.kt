package com.example.services

import com.example.models.Article
import com.example.repositories.ArticleRepository

class ArticleService {
    private val articleRepository by lazy { ArticleRepository() }

    fun getAll() = articleRepository.getAll()

    fun create(title: String, body: String): ArticleResult = when {
        title.isBlank() -> ArticleResult.BadTitle()
        body.isBlank() -> ArticleResult.BadBody()
        else -> when (val article = articleRepository.create(title, body)) {
            null -> ArticleResult.UnknownError()
            else -> ArticleResult.Success(article)
        }
    }
}

sealed class ArticleResult(open val message: String = "") {
    class BadTitle: ArticleResult("empty_title")
    class BadBody: ArticleResult("body_empty")
    class UnknownError: ArticleResult("unknown_error")
    class Success(val body: Article): ArticleResult()
}