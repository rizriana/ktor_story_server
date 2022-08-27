package com.secondlab.security

import io.ktor.server.auth.*

data class UserIdPrinciple(
    val id: Int
): Principal
