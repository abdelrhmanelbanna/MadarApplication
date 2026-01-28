package com.example.domain.validator

sealed class ValidationResult {
    object Success : ValidationResult()
    data class Error(val field: FieldType, val message: String) : ValidationResult()
}

enum class FieldType {
    NAME, AGE, JOB, GENDER
}