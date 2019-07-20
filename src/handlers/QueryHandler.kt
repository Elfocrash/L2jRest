package com.elfoworks.handlers

import com.elfoworks.models.ApiResponse
import io.ktor.http.Parameters
import org.koin.core.KoinComponent

interface QueryHandler<TResponse> : KoinComponent where TResponse : ApiResponse {
    fun handle(request: Parameters): TResponse
}