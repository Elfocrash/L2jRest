package com.elfoworks.models

import com.elfoworks.models.players.PlayerInfo

interface ApiResponse

class GetAllPlayersResponse(val players: List<PlayerInfo>) : ApiResponse