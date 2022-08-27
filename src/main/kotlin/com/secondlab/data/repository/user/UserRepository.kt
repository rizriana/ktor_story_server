package com.secondlab.data.repository.user

import com.secondlab.base.BaseResponse

interface UserRepository {
    suspend fun getUser(id: Int): BaseResponse<Any>
}