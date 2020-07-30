package com.soramitsukhmer.contactmanagement.api.request

data class LocationDTO(
        val id: Long,
        val name : String
)
data class UpdateLocationDTO(
        var name: String,
        var locationId: Long
)