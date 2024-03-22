package com.example.models

import com.example.models.responses.FullArticleResponse
import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

data class Article(val id: Int, val title: String, val body: String) { companion object }

object Articles : Table() {
    val id = integer("id").autoIncrement()
    val title = varchar("title", 128)
    val body = text("body")

    override val primaryKey = PrimaryKey(id)
}

fun Article.toFullResponse() = FullArticleResponse(id, title, body)
fun List<Article>.toFullListResponse() = map { it.toFullResponse() }

fun ResultRow.toArticle() = Article(
    id = this[Articles.id],
    title = this[Articles.title],
    body = this[Articles.body]
)

fun Query.toArticles() = this.map { it.toArticle() }