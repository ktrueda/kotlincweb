package com.kotlincweb.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting() {

    routing {
        static("/"){
            staticBasePackage = "static"
            resource("index.html")
            defaultResource("index.html")
        }
        get("/hello") {
            call.respondText("Hello World!")
        }
    }
}
