package com.secondlab

import com.secondlab.data.db.DatabaseFactory
import com.secondlab.data.repository.UserRepository
import com.secondlab.data.repository.UserRepositoryImpl
import com.secondlab.routes.authRoutes
import com.secondlab.security.configureSecurity
import com.secondlab.data.service.UserService
import com.secondlab.data.service.UserServiceImpl
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        DatabaseFactory.init()
        install(ContentNegotiation) {
            jackson()
        }
        configureSecurity()
        val service: UserService = UserServiceImpl()
        val repository: UserRepository = UserRepositoryImpl(service)
        authRoutes(repository)

        routing {
            authenticate {
                get("/testurl") {
                    call.respond("Working Fine!")
                }
            }
        }
    }.start(wait = true)
}
