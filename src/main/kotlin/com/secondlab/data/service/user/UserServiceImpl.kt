package com.secondlab.data.service.user

import com.secondlab.data.db.DatabaseFactory.dbQuery
import com.secondlab.data.db.extensions.toUser
import com.secondlab.data.db.schemas.UserTable
import com.secondlab.data.models.User
import org.jetbrains.exposed.sql.select

class UserServiceImpl : UserService {
    override suspend fun getUser(id: Int): User? {
        val userRow = dbQuery { UserTable.select { UserTable.id eq id }.first() }
        return userRow.toUser()
    }
}