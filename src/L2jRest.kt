package com.elfoworks

import com.elfoworks.annotations.Get
import com.elfoworks.annotations.Post
import com.elfoworks.handlers.CommandHandler
import com.elfoworks.handlers.QueryHandler
import com.elfoworks.ioc.iocModule
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.gson
import io.ktor.http.HttpStatusCode
import io.ktor.request.receiveText
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.koin.ktor.ext.Koin
import org.reflections.Reflections

object L2jRestApi{
    fun start() {
        embeddedServer(Netty, port = 6969){
            install(DefaultHeaders)
            
            install(ContentNegotiation){
                gson{}
            }
            
            install(Koin){
                modules(iocModule)
            }
            
            routing { 
                val reflections = Reflections("com.elfoworks")
                val queryHandlers = reflections.getSubTypesOf(QueryHandler::class.java)
                val commandHandlers = reflections.getSubTypesOf(CommandHandler::class.java)
                
                for(handler in queryHandlers.filter { x -> x.isAnnotationPresent(Get::class.java) }) {                    
                    get("/api/${handler.getAnnotation(Get::class.java).route}"){
                        val response = handler.newInstance().handle(call.parameters)
                        if(response == null) {
                            call.respond(HttpStatusCode.NotFound)
                            return@get
                        }
                        call.respond(response)
                    }
                }

                for(handler in commandHandlers.filter { x -> x.isAnnotationPresent(Post::class.java) }) {
                    post("/api/${handler.getAnnotation(Post::class.java).route}"){
                        val response = handler.newInstance().handle(call.parameters, call.receiveText())
                        if(response == null) {
                            call.respond(HttpStatusCode.NotFound)
                            return@post
                        }
                        call.respond(response)
                    }
                }
            }
        }.start(wait = false)
    }    
}