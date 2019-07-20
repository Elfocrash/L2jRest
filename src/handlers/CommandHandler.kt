package com.elfoworks.handlers

import com.elfoworks.models.ApiRequest
import com.elfoworks.models.ApiResponse

interface CommandHandler<TRequest, TResponse> where TRequest : ApiRequest, TResponse : ApiResponse? {
    fun handle(request: TRequest): TResponse
}