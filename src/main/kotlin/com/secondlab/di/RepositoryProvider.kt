package com.secondlab.di

import com.secondlab.data.repository.auth.AuthRepository
import com.secondlab.data.repository.auth.AuthRepositoryImpl
import com.secondlab.data.repository.user.UserRepository
import com.secondlab.data.repository.user.UserRepositoryImpl
import com.secondlab.data.service.auth.AuthServiceImpl
import com.secondlab.data.service.user.UserServiceImpl

object RepositoryProvider {
    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl(AuthServiceImpl())
    fun provideUserRepository(): UserRepository = UserRepositoryImpl(UserServiceImpl())
}