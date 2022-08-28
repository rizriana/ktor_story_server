package com.secondlab.data.repository.user

import com.secondlab.base.BaseResponse
import com.secondlab.data.service.user.UserService

class UserRepositoryImpl(
    private val userService: UserService
) : UserRepository {
    override suspend fun getUser(id: Int): BaseResponse<Any> {
        return BaseResponse.SuccessResponse(success = true, data = userService.getUser(id))
    }
}