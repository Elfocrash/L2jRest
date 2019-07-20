package com.elfoworks.services

import com.elfoworks.models.players.PlayerResponse
import com.elfoworks.sql.SqlQueries
import net.sf.l2j.L2DatabaseFactory
import java.sql.SQLException

interface PlayerService {
    fun getAllPlayers(): List<PlayerResponse>
    fun getPlayerById(playerId: Int): PlayerResponse?
}

class PlayerServiceImpl : PlayerService {
    override fun getPlayerById(playerId: Int): PlayerResponse? {
        try {
            L2DatabaseFactory.getInstance().connection.use { con ->
                val statement = con.prepareStatement(SqlQueries.GetAllPlayers)
                val resultSet = statement.executeQuery()

                var player: PlayerResponse? = null
                if (resultSet.next())
                {
                    val id = resultSet.getInt("obj_Id")
                    val name = resultSet.getString("char_name")
                    val level = resultSet.getInt("level")
                    val isOnline = resultSet.getInt("online") == 1
                    val pvpKills = resultSet.getInt("pvpkills")
                    val pkKills = resultSet.getInt("pkkills")
                    val isNobless = resultSet.getInt("nobless") == 1
                    player = PlayerResponse(id, name, level, isOnline, pvpKills, pkKills, isNobless)
                }

                resultSet.close()
                statement.close()
                return player
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return null
    }

    override fun getAllPlayers(): List<PlayerResponse> {
        val players = ArrayList<PlayerResponse>()
        try {
            L2DatabaseFactory.getInstance().connection.use { con ->
                val statement = con.prepareStatement(SqlQueries.GetAllPlayers)
                val resultSet = statement.executeQuery()

                while (resultSet.next())
                {
                    val id = resultSet.getInt("obj_Id")
                    val name = resultSet.getString("char_name")                    
                    val level = resultSet.getInt("level")
                    val isOnline = resultSet.getInt("online") == 1
                    val pvpKills = resultSet.getInt("pvpkills")
                    val pkKills = resultSet.getInt("pkkills")
                    val isNobless = resultSet.getInt("nobless") == 1
                    players.add(PlayerResponse(id, name, level, isOnline, pvpKills, pkKills, isNobless))
                }

                resultSet.close()
                statement.close()
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }

        return players
    }
}