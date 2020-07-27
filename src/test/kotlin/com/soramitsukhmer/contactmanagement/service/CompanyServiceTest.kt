package com.soramitsukhmer.contactmanagement.service

import com.soramitsukhmer.contactmanagement.domain.model.Company
import com.soramitsukhmer.contactmanagement.domain.model.Status
import com.soramitsukhmer.contactmanagement.repository.StatusRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.test.util.AssertionErrors.*

@SpringBootTest
class CompanyServiceTest(
){
    @Autowired
    lateinit var companyService: CompanyService
    @Autowired
    lateinit var statusRepository: StatusRepository

    @Test
    fun testCreateCompany(){
        statusRepository.save(Status(1,"Active"))
        val createdCompany = companyService.createCompany(CompanyServiceTestHelper.validCompanyReqDTO)
        assertTrue("Created Company Is Not Null", createdCompany.id != null)
        assertEquals("Test if name is difference", CompanyServiceTestHelper.validCompanyReqDTO.name, createdCompany.name)
        assertEquals("Test if phone is difference", CompanyServiceTestHelper.validCompanyReqDTO.phone, createdCompany.phone)
        companyService.deleteCompany(createdCompany.id)
    }

    @Test
    fun testUpdateCompany(){
        statusRepository.save(Status(1,"Active"))
        val createdCompany = companyService.createCompany(CompanyServiceTestHelper.validCompanyReqDTO)
        var newUpdateRequest = CompanyServiceTestHelper.validCompanyReqDTO
        newUpdateRequest.name = "New Updated"
        val updatedCompany = companyService.updateCompany(createdCompany.id, newUpdateRequest)
        assertNotEquals("Updated Company Name Not Equals with Old Name", createdCompany.name, updatedCompany.name)
    }
    @Test
    fun testListAllCompanies() {
        statusRepository.save(Status(1,"Active"))
        val createdCompany = companyService.createCompany(CompanyServiceTestHelper.validCompanyReqDTO)
        val listAllCompanies = companyService.listAllCompanies()
        assertTrue("Companies is not empty",listAllCompanies.isNotEmpty())
        companyService.deleteCompany(createdCompany.id)
    }
    @Test
    fun testDeleteByIDCompany() {
        statusRepository.save(Status(1,"Active"))
        val createdCompany = companyService.createCompany(CompanyServiceTestHelper.validCompanyReqDTO)
        companyService.deleteCompany(createdCompany.id)
        val listAllCompanies = companyService.listAllCompanies()
        assertTrue("Company ID deleted",listAllCompanies.isEmpty())

    }
}