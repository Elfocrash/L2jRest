package com.elfoworks

import com.elfoworks.ioc.iocModule
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.gson
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.koin.ktor.ext.Koin
import kotlin.reflect.full.createInstance

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
                for(request in RequestMap.queryRequests) {
                    get("/api/${request.key}"){
                        val response = request.value.createInstance().handle(call.request.queryParameters)
                        call.respond(response)
                    }
                }
            }
        }.start(wait = false)
    }    
}