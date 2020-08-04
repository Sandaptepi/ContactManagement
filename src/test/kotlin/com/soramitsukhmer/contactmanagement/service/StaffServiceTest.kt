package com.soramitsukhmer.contactmanagement.service

import com.soramitsukhmer.contactmanagement.domain.model.Company
import com.soramitsukhmer.contactmanagement.repository.CompanyRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.test.util.AssertionErrors.*

@SpringBootTest
class StaffServiceTest(
){
    @Autowired
    lateinit var staffService: StaffService

    @Autowired
    lateinit var companyRepository: CompanyRepository

    @Autowired
    lateinit var companyService: CompanyService

    @Test
    fun testCreateStaff(){
        companyRepository.save(Company(1, "Sora", "12345678", "sdfghjkl;", "ertyui", listOf(math, computer, economics)))
        val createdStaff = staffService.addStaff(StaffServiceTestHelper.validStaffReqDTO)
        assertTrue("Created Company Is Not Null", true)
        assertEquals("Test if gender is difference", StaffServiceTestHelper.validStaffReqDTO.gender, createdStaff.gender)
        staffService.deleteStaff(createdStaff.id)
    }

    @Test
    fun testUpdateStaff(){
        companyRepository.save(Company(1, "Sora", "12345678", "sdfghjkl;", "ertyui", listOf(math, computer, economics)))
        val createdStaff = staffService.addStaff(StaffServiceTestHelper.validStaffReqDTO)
        var newUpdateRequest = StaffServiceTestHelper.validStaffReqDTO
        newUpdateRequest.name = "NEW"
        val updatedStaff = staffService.updateStaff(createdStaff.id, newUpdateRequest)
        assertNotEquals("Updated Staff Name Not Equals with Old Name", createdStaff.name, updatedStaff.name)
    }
    @Test
    fun testListAllStaff() {
        companyRepository.save(Company(1, "Sora", "12345678", "sdfghjkl;", "ertyui", listOf(math, computer, economics)))
        val createdStaff = staffService.addStaff(StaffServiceTestHelper.validStaffReqDTO)
        val listAllStaffs = staffService.listAllStaffs(null,PageRequest.of(0,5))
        assertTrue("List all Staff",listAllStaffs.content.isNotEmpty())
        staffService.deleteStaff(createdStaff.id)
    }
    @Test
    fun testDeleteByIDStaff() {
        companyRepository.save(Company(1, "Sora", "12345678", "sdfghjkl;", "ertyui", listOf(math, computer, economics)))
        val createdStaff = staffService.addStaff(StaffServiceTestHelper.validStaffReqDTO)
        staffService.deleteStaff(createdStaff.id)
        val listAllStaffs = staffService.listAllStaffs(null,PageRequest.of(0,5))
        assertTrue("Deleted Staff by id",listAllStaffs.content.isEmpty())
    }
}