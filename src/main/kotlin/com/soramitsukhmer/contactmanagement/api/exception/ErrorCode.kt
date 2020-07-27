package com.soramitsukhmer.contactmanagement.api.exception

enum class ErrorCode(
        val code: Int, var message: String
) {
    COMPANY(0, "company"),
    STAFF(1, "staff"),
    IS_NOTFOUND(2, "is not found")
}