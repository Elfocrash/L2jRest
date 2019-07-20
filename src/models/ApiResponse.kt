package com.elfoworks.models

import com.elfoworks.models.players.PlayerResponse

interface ApiResponse

class GetAllPlayersResponse(val players: List<PlayerResponse>) : ApiResponse