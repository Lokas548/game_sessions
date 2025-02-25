package com.mironov.model

data class User(
    var username: String,
    val email:String,
    val passwordHash:String,
    val createdAt: String
)
