package com.secondlab.data.repository.auth

import com.secondlab.base.BaseResponse
import com.secondlab.config.*
import com.secondlab.data.service.auth.AuthService
import com.secondlab.routes.auth.CreateUserParams
import com.secondlab.routes.auth.UserLoginParams
import com.secondlab.security.JwtConfig

class AuthRepositoryImpl(
    private val authService: AuthService
) : AuthRepository {
    override suspend fun registerUser(params: CreateUserParams): BaseResponse<Any> {
        return if (isEmailExist(params.email)) {
            BaseResponse.ErrorResponse(success = false, message = MESSAGE_EMAIL_ALREADY_REGISTERED)
        } else {
            val user = authService.registerUser(params)
            if (user != null) {
                val token = JwtConfig.instance.createAccessToken(user.id)
                user.authToken = token
                BaseResponse.SuccessResponse(success = true, data = user, message = USER_REGISTRATION_SUCCESS)
            } else {
                BaseResponse.ErrorResponse(success = false, GENERIC_ERROR)
            }
        }
    }

    override suspend fun loginUser(params: UserLoginParams): BaseResponse<Any> {
        val user = authService.loginUser(params.email, params.password)
        return if (user != null) {
            val token = JwtConfig.instance.createAccessToken(user.id)
            user.authToken = token
            BaseResponse.SuccessResponse(success = true, data = user, message = USER_LOGIN_SUCCESS)
        } else {
            BaseResponse.ErrorResponse(success = false, USER_LOGIN_FAILURE)
        }
    }

    private suspend fun isEmailExist(email: String): Boolean {
        return authService.findUserByEmail(email) != null
    }
}