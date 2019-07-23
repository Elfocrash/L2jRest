package com.elfoworks.features

import io.ktor.application.ApplicationCall
import io.ktor.application.ApplicationCallPipeline
import io.ktor.application.ApplicationFeature
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.header
import io.ktor.response.respond
import io.ktor.util.AttributeKey
import io.ktor.util.pipeline.PipelineContext

class ApiKeyAuthentication(configuration: Configuration) {
    private val apiKey = configuration.apiKey
    
    class Configuration {
        var apiKey = ""
    }

    private suspend fun intercept(context: PipelineContext<Unit, ApplicationCall>) {
        val headerApiKey = context.call.request.header("x-api-key")
        if(headerApiKey != apiKey){
           context.call.respond(HttpStatusCode.Unauthorized) 
        }
    }
    
    companion object Feature : ApplicationFeature<ApplicationCallPipeline, ApiKeyAuthentication.Configuration, ApiKeyAuthentication> {
        override val key = AttributeKey<ApiKeyAuthentication>("ApiKeyAuthentication")
        override fun install(pipeline: ApplicationCallPipeline, configure: Configuration.() -> Unit): ApiKeyAuthentication {
            val configuration = Configuration().apply(configure)
            
            val feature = ApiKeyAuthentication(configuration)
            
            pipeline.intercept(ApplicationCallPipeline.Call) {
                feature.intercept(this)
            }
            
            return feature
        }
    }
}