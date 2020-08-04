package com.soramitsukhmer.contactmanagement.api.request

import com.fasterxml.jackson.annotation.JsonFormat
import com.soramitsukhmer.contactmanagement.common.Constants
import java.time.LocalDateTime
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class CompanyDTO(
        val id: Long,
        val name: String,
        val phone: String,
        val webUrl: String?,
        val status: StatusDTO,
        val locations: List<LocationDTO>,
        @JsonFormat(pattern = Constants.DATETIME_FORMAT)
        val createdAt: LocalDateTime,
        @JsonFormat(pattern = Constants.DATETIME_FORMAT)
        val updatedAt: LocalDateTime
)

data class CompanyNameDTO(
        var name: String
)

data class CompanyPageableDTO(
        val id: Long,
        val name: String,
        val phone: String,
        val webUrl: String?,
        val status: StatusDTO,
        @JsonFormat(pattern = Constants.DATETIME_FORMAT)
        val createdAt: LocalDateTime,
        @JsonFormat(pattern = Constants.DATETIME_FORMAT)
        val updatedAt: LocalDateTime,
        val pageable: Pagination
)
data class FilterParamsCompanyDTO(
        val q: String?,
        val statusId: Long?
)

data class RequestCompanyDTO(
        @field:NotEmpty var name: String,
        @field:NotEmpty val phone: String,
        val webUrl: String?,
        @field:NotNull val statusId: Long,
        @field:NotNull var locations: List<Long>
)

data class RequestCompanyWithStaffDTO(
        @field:NotEmpty var name: String,
        @field:NotEmpty var phone: String,
        var webUrl: String?,
        @field:NotNull var status: Long,
        @field:NotNull var locations: List<Long>,
        @field:NotNull var staffs: List<RequestStaffDTO>
)
