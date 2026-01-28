package com.example.domain.validator

import javax.inject.Inject

class UserValidator @Inject constructor() {

    fun validate(name: String, age: String, job: String, gender: String): ValidationResult {

        return when {
            name.isBlank() -> ValidationResult.Error(FieldType.NAME, "Name cannot be empty")
            age.isBlank() -> ValidationResult.Error(FieldType.AGE, "Age is required")
            age.toIntOrNull() == null -> ValidationResult.Error(FieldType.AGE, "Age must be a valid number")
            age.toInt() < 1 -> ValidationResult.Error(FieldType.AGE, "Age must be greater than 0")
            job.isBlank() -> ValidationResult.Error(FieldType.JOB, "Job title is required")
            gender.isBlank() -> ValidationResult.Error(FieldType.GENDER, "Please select a gender")
            else -> ValidationResult.Success
        }
    }
}