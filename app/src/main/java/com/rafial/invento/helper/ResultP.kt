package com.rafial.invento.helper

sealed class ResultP<out R> private constructor() {
    data class Success<out T>(val data: T) : ResultP<T>()
    data class Error(val error: String) : ResultP<Nothing>()
    object Loading : ResultP<Nothing>()
}

