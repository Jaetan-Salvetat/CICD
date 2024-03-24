package com.example.repositories

import com.example.models.Article
import com.example.models.Articles
import com.example.models.toArticle
import com.example.models.toArticles
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class ArticleRepository {
    fun getAll(): List<Article> = transaction {
        Articles.selectAll().toArticles()
    }

    fun create(title: String, body: String): Article? = transaction {
        val query = Articles.insert {
            it[Articles.title] = title
            it[Articles.body] = body
        }
        query.resultedValues?.first()?.toArticle()
    }
}