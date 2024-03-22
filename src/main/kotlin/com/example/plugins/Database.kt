package com.example.plugins

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureMySql() {
    Database.connect(
        url = "jdbc:mysql://localhost:3306/KtorPock",
        driver = "com.mysql.cj.jdbc.Driver",
        user = "root",
        password = "root"
    )

    createTables()
}

private fun createTables() {
    transaction {
        exec("CREATE DATABASE IF NOT EXISTS KtorSample")
        exec("CREATE TABLE IF NOT EXISTS Articles (id INTEGER PRIMARY KEY, title VARCHAR(255), body TEXT)")
    }
}