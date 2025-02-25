package com.mironov

import com.mironov.DatabaseFactory.databaseInit
import com.mironov.model.User
import com.mironov.repository.UserRepository
import com.mironov.routing.configureRouting
import com.mironov.security.configureSecurity
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    databaseInit();

    configureHTTP()
    configureSecurity()
    configureMonitoring()
    configureRouting()
}
