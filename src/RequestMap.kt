package com.elfoworks

import com.elfoworks.handlers.players.GetAllPlayersHandler

object RequestMap {
    val queryRequests = hashMapOf(
        "players" to GetAllPlayersHandler::class)
}