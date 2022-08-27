package com.secondlab.data.repository.auth

import com.secondlab.base.BaseResponse
import com.secondlab.routes.auth.CreateUserParams
import com.secondlab.routes.auth.UserLoginParams

interface AuthRepository {
    suspend fun registerUser(params: CreateUserParams): BaseResponse<Any>
    suspend fun loginUser(params: UserLoginParams): BaseResponse<Any>
}