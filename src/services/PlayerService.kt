package com.elfoworks.services

import com.elfoworks.models.players.PlayerInfo
import net.sf.l2j.L2DatabaseFactory
import java.sql.SQLException

interface PlayerService {
    fun getAllPlayers(): List<PlayerInfo>
}

class PlayerServiceImpl : PlayerService {
    
    override fun getAllPlayers(): List<PlayerInfo> {
        val players = ArrayList<PlayerInfo>()
        try {
            L2DatabaseFactory.getInstance().connection.use { con ->
                val statement = con.prepareStatement("SELECT char_name, level FROM characters")
                val resultSet = statement.executeQuery()

                while (resultSet.next())
                {
                    val name = resultSet.getString("char_name")
                    val level = resultSet.getInt("level")
                    players.add(PlayerInfo(name, level))
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