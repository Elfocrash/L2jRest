package com.elfoworks.handlers.players

import com.elfoworks.annotations.Get
import com.elfoworks.handlers.QueryHandler
import com.elfoworks.models.GetAllPlayersResponse
import com.elfoworks.services.PlayerService
import io.ktor.http.Parameters
import org.koin.core.inject

@Get("players") class GetAllPlayersHandler : QueryHandler<GetAllPlayersResponse> {
    private val playerService : PlayerService by inject()
    
    override fun handle(request: Parameters): GetAllPlayersResponse {
        val players = playerService.getAllPlayers()
        return GetAllPlayersResponse(players)
    }
}