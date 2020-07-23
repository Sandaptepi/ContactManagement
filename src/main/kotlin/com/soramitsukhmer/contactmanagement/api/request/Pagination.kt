package com.soramitsukhmer.contactmanagement.api.request

import com.soramitsukhmer.contactmanagement.service.CompanyService


data class PageResponse<T>(val content: List<T>, val pagination: Pagination) {}
data class Pagination(
        val currentPage: Int, val pageSize: Int, val totalElements: Long, val totalPages: Int
)