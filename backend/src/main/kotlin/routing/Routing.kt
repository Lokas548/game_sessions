package com.mironov.routing

import com.mironov.model.User
import com.mironov.repository.UserRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        post("/api/v1/add-user") {
            val userRepository = UserRepository()

            /*val user = User(
                email = "kis@mail.ru",
                username = "adad",
                passwordHash = "asdgfaweg23gvs",
                createdAt = "1,2,3,3"
            )*/

            userRepository.insert(user)
            call.respondText("User added", status = HttpStatusCode.Created)
        }
    }

}
