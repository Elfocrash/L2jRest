package com.elfoworks.models.players

import com.elfoworks.models.ApiResponse

class PlayerResponse(val id: Int, val name: String, val level: Int, val isOnline: Boolean, val pvpKills: Int, val pkKills: Int, val isNobless: Boolean) : ApiResponse