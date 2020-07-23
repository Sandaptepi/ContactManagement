package com.soramitsukhmer.contactmanagement.api.request


data class StatusDTO (
        val name: String
)

data class UpdateStatusDTO (
        val statusId : Long,
        val name: String
)