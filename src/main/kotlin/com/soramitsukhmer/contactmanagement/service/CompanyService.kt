package com.soramitsukhmer.contactmanagement.service

import com.soramitsukhmer.contactmanagement.api.exception.FieldNotFoundException
import com.soramitsukhmer.contactmanagement.api.request.*
import com.soramitsukhmer.contactmanagement.api.response.toPageResponse
import com.soramitsukhmer.contactmanagement.domain.model.*
import com.soramitsukhmer.contactmanagement.domain.model.Company.Companion.fromReqDTO
import com.soramitsukhmer.contactmanagement.domain.spec.CompanySpec
import com.soramitsukhmer.contactmanagement.repository.*
import com.soramitsukhmer.contactmanagement.service.validation.CompanyValidationService
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import javax.validation.Valid


@Service
class CompanyService(
        val companyRepository: CompanyRepository,
        val statusRepository: StatusRepository,
        val companyValidationService: CompanyValidationService,
        val locationRepository: LocationRepository,
        val companyLocationRepository: CompanyLocationRepository,
        val staffRepository: StaffRepository
) {
    fun listAllCompanies(): List<CompanyDTO> {
        return companyRepository.findAll().map {
            it.toDTO()
        }
    }

//    fun findAllCompaniesByLocationId(locationId: Long): List<Company> {
//        val findById = locationRepository.findById(locationId).orElseThrow{
//            RuntimeException("ID Not Found")
//        }
//        val companies = findById.companies
//        ////  Update Location
//        /**
//         * + companyId -> Company Object, company.locations
//         * + new Location/ existed locationId(s) -> Location Object(s)
//         */
//    }

    fun listAllCompanies(@Valid filterParamsCompanyDTO: FilterParamsCompanyDTO, pageable: Pageable)
            : PageResponse<CompanyDTO> {
        val querySpec = filterParamsCompanyDTO.q?.let { CompanySpec.genSearchSpec(it.toLowerCase()) }
        val statusSpec = filterParamsCompanyDTO.statusId?.let { CompanySpec.genFilterStatus(it) }

        val and = Specification.where(querySpec)?.and(statusSpec)
        return companyRepository.findAll(and, pageable).map {
            it.toDTO()
        }.toPageResponse()
    }

    fun getCompany(id: Long): CompanyDTO {
        return companyRepository.findById(id).orElseThrow {
            throw FieldNotFoundException(Company::id.name, "$id")
        }.toDTO()
    }

//    fun createCompany(reqCompanyDTO: RequestCompanyDTO): CompanyDTO {
//        val status = statusRepository.findById(reqCompanyDTO.statusId).get()
//        val newCompany = fromReqDTO(reqCompanyDTO, status)
//        companyValidationService.validateUniquePhone(null, newCompany.phone, newCompany.name)
//        return companyRepository.save(newCompany).toDTO()
//    }

    fun createCompany(reqCompanyDTO: RequestCompanyDTO): CompanyDTO {
        val status = statusRepository.findById(reqCompanyDTO.statusId).orElseThrow{
            throw FieldNotFoundException(Company::status.name, "${reqCompanyDTO.statusId}")
        }
        val reqCompany = fromReqDTO(reqCompanyDTO,status)
        companyValidationService.validateUniquePhoneAndName(null, reqCompany.phone, reqCompany.name)

        val createdCompany = companyRepository.save(reqCompany)
        createdCompany.companyLocations = locationRepository.findAllById(reqCompanyDTO.locations).map {
            val companyLocation = CompanyLocation().apply {
                this.company = createdCompany
                this.locations = it
            }
            companyLocationRepository.save(companyLocation)
        }
        return createdCompany.toDTO()
    }

    fun createCompanyWithStaffs(req: RequestCompanyWithStaffDTO): CompanyDTO {
        val companyDTO = RequestCompanyDTO(
                name = req.name,
                phone = req.phone,
                webUrl = req.webUrl,
                statusId = req.status,
                locations = req.locations
        )
        val createdCompany = createCompany(companyDTO)
        req.staffs.map { staffDTO ->
            val company = companyRepository.findById(createdCompany.id).orElseThrow {
                throw FieldNotFoundException(Company::id.name ,"${createdCompany.id}")
            }
            val staff = Staff.fromDTO(staffDTO, company)
            staffRepository.save(staff)
        }
        return createdCompany
    }

//
//    fun updateCompany(id: Long, dto: RequestCompanyDTO) : CompanyDTO{
//        val status = statusRepository.findById(dto.statusId)
//                .orElseThrow { throw FieldNotFoundException(Status::id.name, "$id") }
//        val originCompany = companyRepository.findById(id).get()
//        val newCompany = fromReqDTO(dto,originCompany, status)
//        companyValidationService.validateUniquePhoneAndName(newCompany.id,newCompany.phone,newCompany.name)
//        return companyRepository.save(newCompany).toDTO()
//    }

    fun updateCompany(id: Long, reqCompanyDTO: RequestCompanyDTO) : CompanyDTO{
        val status = statusRepository.findById(reqCompanyDTO.statusId).orElseThrow{
            throw FieldNotFoundException(Status::id.name, "$reqCompanyDTO.status")
        }
        val company = companyRepository.findById(id).orElseThrow{
            throw FieldNotFoundException(Company::id.name, "$id")
        }.updateCompany(reqCompanyDTO, status)

        companyLocationRepository.deleteAll(company.companyLocations)
        locationRepository.findAllById(reqCompanyDTO.locations).map {
            val companyLocation = CompanyLocation().apply {
                this.company = company
                this.locations = it
            }
            companyLocationRepository.save(companyLocation)
        }

        companyValidationService.validateUniquePhoneAndName(company.id, company.phone, company.name)

        return companyRepository.save(company).toDTO()
    }

    fun deleteCompany (id: Long) : String {
        val company = companyRepository.findById(id).orElseThrow{
            throw FieldNotFoundException(Company::id.name, "$id")
        }
        try {
            companyRepository.delete(company)
        } catch (e: Exception) {
            throw ErrorCompanyException()
        }
      return "DELETED"
        }
}
