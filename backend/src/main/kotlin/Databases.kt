package com.mironov

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.fasterxml.jackson.databind.*
import com.typesafe.config.ConfigFactory
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.config.*
import io.ktor.server.plugins.calllogging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.sql.Connection
import java.sql.DriverManager
import org.jetbrains.exposed.sql.*
import org.postgresql.util.PSQLException
import org.slf4j.event.*
import java.sql.SQLException
import java.util.Properties

object DatabaseFactory{

    val config = HikariConfig().apply {
        jdbcUrl = System.getenv("PG_DATABASE_URL")
        driverClassName = "org.postgresql.Driver"
        username = System.getenv("PG_DATABASE_USER")
        password = System.getenv("PG_DATABASE_PASSWORD")
    }

    fun Application.databaseInit(){
        try {
            Database.connect(HikariDataSource(config))
            println("Database connection success")
        } catch (e: PSQLException) {
            println("Database connection trouble: ${e.message}")
            e.printStackTrace()
        } catch (e: Exception) {
            println("An unexpected error occurred: ${e.message}")
            e.printStackTrace()
        }
    }
}