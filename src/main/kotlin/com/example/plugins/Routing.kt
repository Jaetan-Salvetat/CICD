package com.example.plugins

import com.example.models.FullArticleRequest
import com.example.models.Pong
import com.example.models.responses.ErrorResponse
import com.example.models.responses.FullArticleResponse
import com.example.models.toFullListResponse
import com.example.services.ArticleResult
import com.example.services.ArticleService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        get("ping") {
            call.respond(Pong())
        }

        route("articles") {
            val articleService by lazy { ArticleService() }

            get {
                call.respond(articleService.getAll().toFullListResponse())
            }

            post {
                val body = call.receive<FullArticleRequest>()

                when (val result = articleService.create(body.title, body.body)) {
                    is ArticleResult.Success -> call.respond(
                        status = HttpStatusCode.Created,
                        message = FullArticleResponse(
                            id = result.body.id,
                            title = result.body.title,
                            body = result.body.body,
                        )
                    )
                    is ArticleResult.UnknownError -> call.respond(
                        status = HttpStatusCode.InternalServerError,
                        message = ErrorResponse(result.message)
                    )
                    else -> call.respond(
                        status = HttpStatusCode.BadRequest,
                        message = ErrorResponse(result.message)
                    )
                }
            }
        }
    }
}