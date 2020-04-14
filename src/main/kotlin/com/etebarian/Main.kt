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

fun main(args: Array<String>) {
    var server: ApplicationEngine? = null
    try {
        server = embeddedServer(Netty, port = 9696) {
            routing {

                get("/") {
                    call.respond(HttpStatusCode.OK, "UUID")
                }

                get("/sample_cat_breed_create_422") {
                    call.respond(
                        HttpStatusCode.UnprocessableEntity, """
                        [
                            {
                                "field":"name",
                                "message":"choose another name"
                            }
                        ]
                    """.trimIndent()
                    )
                }
                get("/sample_cat_breed_create") {
                    call.respond(
                        HttpStatusCode.OK,
                        """
                            {
                                "status":1,
                                "message":"Breed was created!",
                                "message_persian":"نژاد ساخته شد"
                            }
                        """.trimIndent()
                    )
                }

                get("/sample_cat_breed_detail") {
                    call.respond(
                        HttpStatusCode.OK,
                        """{
    "id": 1,
    "name": "Persian",
    "persian_name": "پرشین",
    "image_url": "http://etebarian.com/meow/persian.jpg"
  }
                    """.trimIndent()
                    )
                }

                get("/sample_cat_breed_index") {
                    call.respond(
                        HttpStatusCode.OK,
                        """[
  {
    "id": 1,
    "name": "Persian",
    "persian_name": "پرشین",
    "image_url": "http://etebarian.com/meow/persian.jpg"
  },
  {
    "id": 2,
    "name": "Bangal",
    "persian_name": "بنگال",
    "image_url": "http://etebarian.com/meow/bangal.jpg"
  },
  {
    "id": 3,
    "name": "Maine",
    "persian_name": "ماین",
    "image_url": "http://etebarian.com/meow/maine.jpg"
  },
  {
    "id": 4,
    "name": "British Shorthair",
    "persian_name": "موکوتاه بریتانیایی",
    "image_url": "http://etebarian.com/meow/british_shorthair.jpg"
  },
  {
    "id": 5,
    "name": "Siamese",
    "persian_name": "سیامسه",
    "image_url": "http://etebarian.com/meow/siamese.jpg"
  },
  {
    "id": 6,
    "name": "Sphynx",
    "persian_name": "اسفینکس",
    "image_url": "http://etebarian.com/meow/sphynx.jpg"
  },
  {
    "id": 7,
    "name": "Ragdoll",
    "persian_name": "عروسکی",
    "image_url": "http://etebarian.com/meow/ragdoll.jpg"
  }
]
""".trimIndent()
                    )
                }

                get("/user/1") {
                    call.respond(
                        HttpStatusCode.OK, """
                        { 
                            "data": {
                                "id": "2",
                                "first_name": "Hamidreza",
                                "last_name": "Etebarian"
                            }
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
                            "first_name": "FN_$it",
                            "last_name": "LN_$it"
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
//                            "last_Fname": "Etebarian"
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


//fun Application.main(){
//    routing {
//        get("/"){
//            call.respond(HttpStatusCode.OK, "UUID")
//        }
//    }
//}
