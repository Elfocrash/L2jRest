package com.elfoworks.handlers

import com.elfoworks.models.ApiResponse
import io.ktor.http.Parameters

interface CommandHandler<TResponse> where TResponse : ApiResponse? {
    fun handle(parameters: Parameters, requestBody: String) : TResponse
}