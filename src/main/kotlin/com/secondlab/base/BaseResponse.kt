package com.secondlab.base

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.ktor.http.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonSerialize
sealed class BaseResponse<T>(
    @JsonIgnore open val statusCode: HttpStatusCode
) {
    @JsonSerialize
    data class SuccessResponse<T>(
        val success: T,
        val data: T? = null,
        val message: String? = null,
        @JsonIgnore override val statusCode: HttpStatusCode = HttpStatusCode.OK
    ) : BaseResponse<T>(statusCode)

    @JsonSerialize
    data class ErrorResponse<T>(
        val success: T,
        val data: T? = null,
        val message: String? = null,
        @JsonIgnore
        override val statusCode: HttpStatusCode = HttpStatusCode.BadRequest
    ) : BaseResponse<T>(statusCode)
}