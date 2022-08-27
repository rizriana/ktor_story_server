package com.secondlab.data.service.user

import com.secondlab.data.models.User

interface UserService {
    suspend fun getUser(id: Int): User?
}