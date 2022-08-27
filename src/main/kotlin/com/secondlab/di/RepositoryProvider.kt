package com.secondlab.di

import com.secondlab.data.repository.auth.AuthRepository
import com.secondlab.data.repository.auth.AuthRepositoryImpl
import com.secondlab.data.service.auth.AuthServiceImpl

object RepositoryProvider {
    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl(AuthServiceImpl())
}