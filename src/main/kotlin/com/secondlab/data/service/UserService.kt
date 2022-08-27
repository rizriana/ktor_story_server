package com.secondlab.data.service

import com.secondlab.data.models.User

interface UserService {
    suspend fun registerUser(params: CreateUserParams): User?
    suspend fun findUserByEmail(email: String): User?
}