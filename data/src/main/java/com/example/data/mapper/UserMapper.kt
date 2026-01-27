package com.example.data.mapper

import com.example.data.model.UserEntityRoom
import com.example.domain.entity.UserEntity

object UserMapper {

    fun toRoom(entity: UserEntity): UserEntityRoom {
        return UserEntityRoom(
            id = entity.id,
            name = entity.name,
            age = entity.age,
            jobTitle = entity.jobTitle,
            gender = entity.gender
        )
    }

    fun toDomain(room: UserEntityRoom): UserEntity {
        return UserEntity(
            id = room.id,
            name = room.name,
            age = room.age,
            jobTitle = room.jobTitle,
            gender = room.gender
        )
    }


}