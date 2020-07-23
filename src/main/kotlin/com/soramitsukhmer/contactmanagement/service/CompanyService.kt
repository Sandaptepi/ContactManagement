package com.soramitsukhmer.contactmanagement.service

import com.soramitsukhmer.contactmanagement.api.request.*
import com.soramitsukhmer.contactmanagement.api.response.toPageResponse
import com.soramitsukhmer.contactmanagement.domain.model.Company.Companion.fromReqDTO
import com.soramitsukhmer.contactmanagement.domain.spec.CompanySpec
import com.soramitsukhmer.contactmanagement.repository.CompanyRepository
import com.soramitsukhmer.contactmanagement.repository.StatusRepository
import com.soramitsukhmer.contactmanagement.service.validation.CompanyValidationService
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import javax.validation.Valid


@Service
class CompanyService(
        val companyRepository: CompanyRepository,
        val statusRepository: StatusRepository,
        val companyValidationService: CompanyValidationService
){
    fun listAllCompanies() : List<CompanyDTO>{
        return companyRepository.findAll().map {
            it.toDTO()
        }
    }

    fun listAllCompanies(@Valid filterParamsCompanyDTO: FilterParamsCompanyDTO,pageable: Pageable)
            : PageResponse<CompanyDTO> {
        val querySpec = filterParamsCompanyDTO.q?.let { CompanySpec.genSearchSpec(it.toLowerCase()) }
        val statusSpec = filterParamsCompanyDTO.statusId?.let { CompanySpec.genFilterStatus(it) }

        val and = Specification.where(querySpec)?.and(statusSpec)
        return companyRepository.findAll(and,pageable).map {
            it.toDTO() }.toPageResponse()
    }

    fun getCompany(id: Long) : CompanyDTO{
        return companyRepository.findById(id).orElseThrow{
            throw RuntimeException("CompanyId[$id] is not found.")
        }.toDTO()
    }

    fun createCompany(reqCompanyDTO: RequestCompanyDTO) : CompanyDTO{
        val status = statusRepository.findById(reqCompanyDTO.statusId).get()
        val newCompany = fromReqDTO(reqCompanyDTO, status)
        companyValidationService.validateUniquePhone(null, newCompany.phone,newCompany.name)
        return companyRepository.save(newCompany).toDTO()
    }

//    fun updateCompany(id: Long, dto: RequestCompanyDTO) : CompanyDTO{
//        val company = companyRepository.findById(id).orElseThrow{
//            throw RuntimeException("CompanyId[$id] is not found.")
//        }.updateCompany(reqCompanyDTO)
//        return companyRepository.save(company).toDTO()
//    }
    fun updateCompany(id: Long, dto: UpdateCompanyDTO) : CompanyDTO{
        val status = statusRepository.findById(dto.statusId)
                .orElseThrow { throw RuntimeException("statusId[$id] is not found.") }
        val originCompany = companyRepository.findById(id).get()
        val newCompany = fromReqDTO(dto,originCompany, status)
        companyValidationService.validateUniquePhone(newCompany.id,newCompany.phone,newCompany.name)
        return companyRepository.save(newCompany).toDTO()
    }

}