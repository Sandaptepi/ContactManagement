package com.soramitsukhmer.contactmanagement.api.exception

import java.lang.RuntimeException

data class FieldNotFoundException(val field: String, val value: String) : RuntimeException("$field[$value] ") {

}
