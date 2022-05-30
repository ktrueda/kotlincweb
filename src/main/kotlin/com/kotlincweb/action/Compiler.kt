package com.kotlincweb.action

@kotlinx.serialization.Serializable
class Request(val code: String, val version: String)

fun compiler(request: Request): String {
    return "from compiler: ${request.code} ${request.version}"
}

