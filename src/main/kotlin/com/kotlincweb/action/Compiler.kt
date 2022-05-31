package com.kotlincweb.action

import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit

@kotlinx.serialization.Serializable
class Request(val code: String, val version: String)

@kotlinx.serialization.Serializable
class Response(val byteCode: String)

fun compiler(request: Request): Response {
    val code = request.code
    val uuid = UUID.randomUUID().toString()
    val tempDirectory = File("/tmp/kotlinc_$uuid")
    runKotlinc(code, tempDirectory)
    val javapOut = runJavap(tempDirectory)
    tempDirectory.deleteRecursively()
    return Response(javapOut)
}

private fun runKotlinc(code: String, workingDir: File) {
    val kotlinFile = File("${workingDir.absoluteFile}/Main.kt")
    kotlinFile.parentFile.mkdirs()
    kotlinFile.writeText(code)
    ProcessBuilder().command("kotlinc", "Main.kt")
        .directory(workingDir)
        .redirectOutput(ProcessBuilder.Redirect.INHERIT)
        .redirectError(ProcessBuilder.Redirect.INHERIT)
        .start().waitFor()

}

private fun runJavap(workingDir: File): String {
    val process = ProcessBuilder("javap", "-v", "MainKt")
        .directory(workingDir)
        .redirectOutput(ProcessBuilder.Redirect.PIPE)
        .redirectError(ProcessBuilder.Redirect.PIPE)
        .start()

    process.waitFor(5, TimeUnit.SECONDS)
    return process.inputStream.bufferedReader().readText()
}

