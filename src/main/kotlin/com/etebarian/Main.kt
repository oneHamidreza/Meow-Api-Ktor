package com.etebarian

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

object Main {

    @JvmStatic
    fun main(args: Array<String>){
        val server = embeddedServer(Netty, port = 8080) {
            routing {
                get("/user/1") {
                    call.respond(
                        HttpStatusCode.OK, """
                        {
                            "id": "1",
                            "first_name": "Hamidreza",
                            "last_name": "Etebarianx"
                        }
                    """.trimIndent())
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
        server.start(wait = true)
    }

}