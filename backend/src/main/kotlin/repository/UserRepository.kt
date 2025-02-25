package com.mironov.repository
import org.jetbrains.exposed.sql.transactions.transaction
import com.mironov.entity.Users
import com.mironov.model.User
import org.jetbrains.exposed.sql.insert

class UserRepository{
    fun insert(user: User): User {
        var id: Int = -1
        transaction {
            Users.insert {
                it[username] = user.username
                it[email] = user.email
                it[passwordHash] = user.passwordHash
                it[createdAt] = user.createdAt
            }

        }
        return User(user.username, user.passwordHash, user.email, user.createdAt)
    }
}