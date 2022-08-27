package com.secondlab.data.repository

import com.secondlab.data.service.CreateUserParams
import com.secondlab.base.BaseResponse

interface UserRepository {
    suspend fun registerUser(params: CreateUserParams): BaseResponse<Any>
    suspend fun loginUser(email: String, password: String): BaseResponse<Any>
}