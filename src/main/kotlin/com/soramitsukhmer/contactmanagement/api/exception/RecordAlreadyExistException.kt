package com.soramitsukhmer.contactmanagement.api.exception

import java.lang.RuntimeException

data class RecordAlreadyExistException(val msg: String) : RuntimeException(msg) {
}