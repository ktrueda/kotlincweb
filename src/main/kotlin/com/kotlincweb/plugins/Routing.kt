package com.kotlincweb.plugins

import com.kotlincweb.action.Request
import com.kotlincweb.action.compiler
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    routing {
        static("/") {
            staticBasePackage = "static"
            resource("index.html")
            resource("js/main.js")
            defaultResource("index.html")
        }
        post("/kotlinc") {
            println("kotlinc called")
            val kotlinCode = call.receive<Request>()
            call.respond(compiler(kotlinCode))
        }
    }
}
