package com.etebarian

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.ApplicationEngine
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlin.system.exitProcess

//fun Application.main(){
//    routing {
//        get("/"){
//            call.respond(HttpStatusCode.OK, "UUID")
//        }
//    }
//}

object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        var server: ApplicationEngine? = null
        try {
            server = embeddedServer(Netty, port = 8888) {
                routing {

                    get("/") {
                        call.respond(HttpStatusCode.OK, "UUID")
                    }

                    get("/user/1") {
                        call.respond(
                            HttpStatusCode.OK, """
                        {
                            "id": "1",
                            "first_name": "Hamidreza",
                            "last_name": "Etebarianx"
                        }
                    """.trimIndent()
                        )
                    }

                    get("/users") {
                        val response = buildString {
                            append("[")
                            (1..100).forEach {
                                append(
                                    """
                        {
                            "id": "$it",
                            "first_name": "Hamidreza",
                            "last_name": "Etebarianx"
                        }
                    """.trimIndent()
                                )
                                val end = if (it == 100) "" else ","
                                append(end)
                            }
                            append("]")
                        }
                        call.respond(HttpStatusCode.OK, response)
                    }

//                post("/user/1") {
//                    call.respondText("""
//                        {
//                            "id": "1",
//                            "first_name": "Hamidreza",
//                            "last_name": "Etebarian"
//                        }
//                    """.trimIndent(), ContentType.Text.Plain)
//                }
                }
            }
            server.start(wait = false)

        } catch (e: Exception) {
            e.printStackTrace()
            server?.stop(0, 0)
            exitProcess(0)
        }
    }

}