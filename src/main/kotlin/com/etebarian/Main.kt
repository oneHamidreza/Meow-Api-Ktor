package com.etebarian

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.server.engine.ApplicationEngine
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlinx.coroutines.delay
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
    fun main(args: Array<String>){
        var server : ApplicationEngine? = null
        try {
        server = embeddedServer(Netty, port = 8888) {
            routing {

                get("/"){
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
                    """.trimIndent())
                }

                get("/users") {
                    call.respond(
                        HttpStatusCode.OK, """
                        [{
                            "id": "1",
                            "first_name": "Hamidreza",
                            "last_name": "Etebarianx"
                        }]
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
        server.start(wait = false)

        }catch (e:Exception){
            e.printStackTrace()
            server?.stop(0,0)
            exitProcess(0)
        }
    }

}