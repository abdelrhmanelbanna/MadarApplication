package com.example.data.mapper

import com.example.data.model.UserEntityRoom
import com.example.domain.entity.UserEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class UserMapperTest {

    @Test
    fun `toRoom should map domain entity to room model correctly`() {

        val domain = UserEntity(id = 10, name = "Ahmad", age = 28, jobTitle = "Dev", gender = "Male")
        val room = UserMapper.toRoom(domain)

        assertEquals(domain.id, room.id)
        assertEquals(domain.name, room.name)
        assertEquals(domain.age, room.age)
        assertEquals(domain.jobTitle, room.jobTitle)
        assertEquals(domain.gender, room.gender)
    }

    @Test
    fun `toDomain should map room model to domain entity correctly`() {
        val room = UserEntityRoom(id = 5, name = "Sara", age = 24, jobTitle = "Designer", gender = "Female")

        val domain = UserMapper.toDomain(room)

        // Then
        assertEquals(room.id, domain.id)
        assertEquals(room.name, domain.name)
        assertEquals(room.age, domain.age)
        assertEquals(room.jobTitle, domain.jobTitle)
        assertEquals(room.gender, domain.gender)
    }
}