package com.mironov.entity

import org.jetbrains.exposed.dao.id.IntIdTable

object UserGames : IntIdTable("user_games") {
    val userId = reference("user_id", Users).index()
    val gameId = reference("game_id", Games).index()
    val rankValue = varchar("rank_value", 255)

    init {
        uniqueIndex(userId, gameId) // Unique constraint on user_id and game_id
    }
}