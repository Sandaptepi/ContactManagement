package com.soramitsukhmer.contactmanagement.api.response

import com.soramitsukhmer.contactmanagement.api.request.PageResponse
import com.soramitsukhmer.contactmanagement.api.request.Pagination
import org.springframework.data.domain.Page

fun <T> Page<T>.toPageResponse(): PageResponse<T> {
    return PageResponse(
            content = this.content,
            pagination = Pagination(
                    currentPage = this.pageable.pageNumber ,
                    pageSize    = this.pageable.pageSize,
                    totalElements = this.totalElements,
                    totalPages = this.totalPages
            )
    )
}