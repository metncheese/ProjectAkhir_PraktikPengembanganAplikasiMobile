package com.dicoding.picodiploma.loginwithanimation.data

data class UserModel(
    val token: String,
    val email: String,
    val isLogin: Boolean = false
)