package com.soramitsukhmer.contactmanagement.repository

import com.soramitsukhmer.contactmanagement.domain.model.CompanyLocation
import org.springframework.data.jpa.repository.JpaRepository

interface CompanyLocationRepository: JpaRepository<CompanyLocation,Long> {
}