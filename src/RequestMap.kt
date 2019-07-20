package com.elfoworks

import com.elfoworks.handlers.players.GetAllPlayersHandler
import com.elfoworks.handlers.players.GetPlayerInfoHandler

object RequestMap {
    val queryRequests = hashMapOf(
        "players" to GetAllPlayersHandler::class,
        "players/{id}" to GetPlayerInfoHandler::class)
}