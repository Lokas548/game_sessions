package com.mironov.entity

import org.jetbrains.exposed.dao.id.IntIdTable

object LobbyMembers : IntIdTable("lobby_members") {
    val lobbyId = reference("lobby_id", Lobbies).index()
    val userId = reference("user_id", Users).index()
    val joinedAt = varchar("joined_at", 50)
}