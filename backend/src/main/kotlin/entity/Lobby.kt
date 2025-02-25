package com.mironov.entity

import org.jetbrains.exposed.dao.id.IntIdTable

object Lobbies : IntIdTable("lobby") {
    val name = varchar("name", 255)
    val ownerId = reference("owner_id", Users).index()
    val createdAt = varchar("created_at",50)
}