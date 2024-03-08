package com.example.plugins

import io.ktor.server.application.*
import io.ktor.server.http.content.*
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
    }
}

private data class Pong(
    val success: Boolean = true,
    val data: String = "Pong!"
)