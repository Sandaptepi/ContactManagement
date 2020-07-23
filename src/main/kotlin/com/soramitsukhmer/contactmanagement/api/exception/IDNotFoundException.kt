package com.soramitsukhmer.contactmanagement.api.exception

import java.lang.RuntimeException

data class IDNotFoundException(val msg: String) : RuntimeException(msg) {

}
