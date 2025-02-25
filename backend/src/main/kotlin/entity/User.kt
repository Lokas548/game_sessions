package com.mironov.entity

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.IDateColumnType
import org.jetbrains.exposed.sql.javatime.datetime
import java.sql.Date
import java.sql.Timestamp
import java.time.LocalDate

object Users : IntIdTable("user"){
    val email = varchar("email", 255).uniqueIndex()
    val passwordHash = varchar("password_hash", 255)
    val username = varchar("username", 50)
    val createdAt = varchar("created_at",50) // Дата создания
}