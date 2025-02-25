package com.mironov.entity

import org.jetbrains.exposed.dao.id.IntIdTable

object Games : IntIdTable("game") {
    val name = varchar("name", 255)
    val genre = varchar("genre", 255)
}