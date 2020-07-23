package com.soramitsukhmer.contactmanagement.repository

import com.soramitsukhmer.contactmanagement.domain.model.Status
import org.springframework.data.repository.CrudRepository

interface StatusRepository: CrudRepository <Status,Long> {

}
