package com.soramitsukhmer.contactmanagement.api.request

import com.fasterxml.jackson.annotation.JsonFormat
import com.soramitsukhmer.contactmanagement.common.Constants
import java.time.LocalDateTime
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class StaffDTO(
        var id: Long,
        var name: String,
        var position: String?,
        var company: CompanyNameDTO,
        var gender: String,
        var location: String?,
        @JsonFormat(pattern = Constants.DATETIME_FORMAT)
        val createdAt: LocalDateTime,
        @JsonFormat(pattern = Constants.DATETIME_FORMAT)
        val updatedAt: LocalDateTime
)
data class FilterParamsStaffDTO(
        val q: String?,
        val companyId: Long?
)
data class RequestStaffDTO(
        @field:NotEmpty var name: String,
        @field:NotEmpty val gender: String,
        val location: String?,
        val position: String?,
        @field:NotNull val companyId: Long
)

data class RequestCompanyStaffDTO(
        @field:NotEmpty var name: String,
        @field:NotEmpty val gender: String,
        val location: String?,
        val position: String?
)