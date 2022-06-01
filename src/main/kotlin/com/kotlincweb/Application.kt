package com.kotlincweb

import com.kotlincweb.plugins.configureRouting
import com.kotlincweb.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.callloging.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureSerialization()
//        install(CORS) {
//            anyHost()
//        }
        install(CallLogging)
    }.start(wait = true)
}
