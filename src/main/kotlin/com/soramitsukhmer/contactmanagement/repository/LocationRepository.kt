package com.soramitsukhmer.contactmanagement.repository

import com.soramitsukhmer.contactmanagement.domain.model.Location
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LocationRepository: JpaRepository<Location, Long> {
}