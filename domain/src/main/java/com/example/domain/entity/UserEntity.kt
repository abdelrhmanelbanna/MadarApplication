package com.example.domain.entity

data class UserEntity(
    val id: Long = 0,
    val name: String,
    val age: Int,
    val jobTitle: String,
    val gender: String
)