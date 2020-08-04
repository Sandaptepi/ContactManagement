package com.soramitsukhmer.contactmanagement.repository

import com.soramitsukhmer.contactmanagement.domain.model.Company
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository

interface CompanyRepository : CrudRepository<Company, Long>, PagingAndSortingRepository<Company,Long>,
        JpaSpecificationExecutor<Company> {
    fun existsCompanyByPhoneOrName(phone: String,name : String) : Boolean
    fun existsCompanyByPhoneAndNameAndIdIsNot(phone: String, name: String, id: Long): Boolean

}