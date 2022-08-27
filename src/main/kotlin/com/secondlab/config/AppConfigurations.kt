package com.secondlab.config

import com.secondlab.data.db.DatabaseFactory
import com.secondlab.di.RepositoryProvider
import com.secondlab.routes.auth.authRoutes
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

fun configureDatabase() {
    DatabaseFactory.init()
}

fun Application.configureContentNegotiation() {
    install(ContentNegotiation) {
        jackson()
    }
}

fun Application.configureRouting() {
    authRoutes(RepositoryProvider.provideAuthRepository())
}