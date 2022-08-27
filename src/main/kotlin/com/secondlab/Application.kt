package com.secondlab

import com.secondlab.config.configureContentNegotiation
import com.secondlab.config.configureDatabase
import com.secondlab.config.configureRouting
import com.secondlab.config.configureStatusPage
import com.secondlab.security.configureSecurity
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        configureDatabase()
        configureContentNegotiation()
        configureStatusPage()
        configureSecurity()
        configureRouting()
    }.start(wait = true)
}
