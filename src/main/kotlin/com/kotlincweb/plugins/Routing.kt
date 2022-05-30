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
            defaultResource("index.html")
        }
        post("/hello") {
            val kotlinCode = call.receive<Request>()
            call.respondText(compiler(kotlinCode))
        }
    }
}
