package com.example.mediplus20.utils

object SessionManager {
    private var _isLoggedIn = false

    val isLoggedIn: Boolean
        get() = _isLoggedIn

    fun login() {
        _isLoggedIn = true
    }

    fun logout() {
        _isLoggedIn = false
    }
}
