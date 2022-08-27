package com.secondlab.data.service.auth

import com.secondlab.data.db.DatabaseFactory.dbQuery
import com.secondlab.data.db.extensions.toUser
import com.secondlab.data.db.schemas.UserTable
import com.secondlab.data.models.User
import com.secondlab.routes.auth.CreateUserParams
import com.secondlab.security.hash
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement

class AuthServiceImpl : AuthService {
    override suspend fun registerUser(params: CreateUserParams): User? {
        var statement: InsertStatement<Number>? = null
        dbQuery {
            statement = UserTable.insert {
                it[email] = params.email
                it[password] = hash(params.password)
                it[fullName] = params.fullName
                it[avatar] = params.avatar
            }
        }
        return statement?.resultedValues?.get(0).toUser()
    }

    override suspend fun loginUser(email: String, password: String): User? {
        val hashedPassword = hash(password)
        val userRow = dbQuery {
            UserTable.select { UserTable.email eq email and (UserTable.password eq hashedPassword) }.firstOrNull()
        }
        return userRow.toUser()
    }

    override suspend fun findUserByEmail(email: String): User? {
        val user = dbQuery {
            UserTable.select { UserTable.email.eq(email) }
                .map { it.toUser() }
                .singleOrNull()
        }
        return user
    }
}