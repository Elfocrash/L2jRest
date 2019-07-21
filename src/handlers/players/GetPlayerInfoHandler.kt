package com.elfoworks.handlers.players

import com.elfoworks.annotations.Get
import com.elfoworks.handlers.QueryHandler
import com.elfoworks.models.players.PlayerResponse
import com.elfoworks.services.PlayerService
import io.ktor.http.Parameters
import org.koin.core.inject

@Get("players/{id}") class GetPlayerInfoHandler : QueryHandler<PlayerResponse?> {
    private val playerService : PlayerService by inject()
    
    override fun handle(request: Parameters): PlayerResponse? {
        val parsedId = request["id"]?.toIntOrNull() ?: return null
        return playerService.getPlayerById(parsedId)
    }
}