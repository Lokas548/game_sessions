package com.mironov.entity

import org.jetbrains.exposed.dao.id.IntIdTable

object Messages : IntIdTable("message") {
    val lobbyId = reference("lobby_id", Lobbies).index()
    val text = varchar("text", 255).nullable() // Nullable if no text is provided
    val pictureLink = varchar("picture_link", 255).nullable() // Nullable if no picture link is provided
    val createdAt = varchar("created_at",50)
    val userId = reference("user_id", Users).index()
}