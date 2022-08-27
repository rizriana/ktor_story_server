package com.secondlab.security

import io.ktor.server.auth.*

data class UserIdPrincipleForUser(
    val id: Int
): Principal
