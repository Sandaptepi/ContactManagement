package com.soramitsukhmer.contactmanagement.service

import com.soramitsukhmer.contactmanagement.api.request.FilterParamsStaffDTO
import com.soramitsukhmer.contactmanagement.api.request.PageResponse
import com.soramitsukhmer.contactmanagement.api.request.RequestStaffDTO
import com.soramitsukhmer.contactmanagement.api.request.StaffDTO
import com.soramitsukhmer.contactmanagement.api.response.toPageResponse
import com.soramitsukhmer.contactmanagement.domain.model.Staff
import com.soramitsukhmer.contactmanagement.domain.spec.StaffSpec
import com.soramitsukhmer.contactmanagement.repository.CompanyRepository
import com.soramitsukhmer.contactmanagement.repository.StaffRepository
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import javax.validation.Valid


@Service
class StaffService(
        val staffRepository: StaffRepository,
        val companyRepository: CompanyRepository
){
    fun listAllStaffs(@Valid filterParamsStaffDTO: FilterParamsStaffDTO?, pageable: Pageable)
            : PageResponse<StaffDTO> {
        val querySpec = filterParamsStaffDTO?.q?.let { StaffSpec.genSearchSpec(it.toLowerCase()) }
        val companySpec = filterParamsStaffDTO?.companyId?.let { StaffSpec.genFilterCompany(it) }

        val and = Specification.where(querySpec)?.and(companySpec)
        return staffRepository.findAll(and, pageable).map { it.toDto() }.toPageResponse()
    }

//    fun listAllStaffs(companyId: Long?, pageable: Pageable) : PageResponse<StaffDTO> {
//        val companySpec = companyId?.let { StaffSpec.genFilterCompany(it) }
//        return staffRepository.findAll(
//                Specification.where(companySpec),
//                pageable
//        ).map{ it.toDto() }.toPageResponse()
//    }

    fun getStaff(id: Long) : StaffDTO{
        return staffRepository.findById(id).orElseThrow{
            throw RuntimeException("StaffId[$id] is not found.")
        }.toDto()
    }
    fun addStaff(requestStaffDTO: RequestStaffDTO) : StaffDTO {
        val company = companyRepository.findById(requestStaffDTO.companyId).orElseThrow{
            throw RuntimeException("CompanyId[${requestStaffDTO.companyId}] is not found.")
        }
        val newStaff = Staff.fromDTO(requestStaffDTO, company)
        return staffRepository.save(newStaff).toDto()
    }
    fun updateStaff(id: Long, requestStaffDTO: RequestStaffDTO) : StaffDTO{
        val staff = staffRepository.findById(id).orElseThrow{
            throw RuntimeException("StaffId[$id] is not found.")
        }.updateStaff(requestStaffDTO)
        return staffRepository.save(staff).toDto()
    }
}