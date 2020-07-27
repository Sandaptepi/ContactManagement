package com.soramitsukhmer.contactmanagement.api.controller

import com.soramitsukhmer.contactmanagement.api.request.*
import com.soramitsukhmer.contactmanagement.repository.CompanyRepository
import com.soramitsukhmer.contactmanagement.service.CompanyService
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@ControllerAdvice
@RestController
@RequestMapping(path = ["/api/v1/companies"])
class CompanyController(
        val companyService: CompanyService,
        val companyRepository: CompanyRepository
){
    @GetMapping
    fun listAllCompanies(@Valid filterParamsCompanyDTO: FilterParamsCompanyDTO, pageable: Pageable)
            : ResponseEntity<PageResponse<CompanyDTO>>{
        return ResponseEntity.ok(companyService.listAllCompanies(filterParamsCompanyDTO,pageable))
    }
    @GetMapping("/{id}")
    fun getCompany(@PathVariable id: Long) : ResponseEntity<CompanyDTO>{
        return ResponseEntity.ok(companyService.getCompany(id))
    }

    @PostMapping
    fun createCompany(@Valid @RequestBody requestCompanyDTO: RequestCompanyDTO) : ResponseEntity<CompanyDTO> {
        return ResponseEntity.ok(companyService.createCompany(requestCompanyDTO))
    }

    @PutMapping("/{id}")
    fun updateCompany(@PathVariable id: Long, @Valid @RequestBody dto: RequestCompanyDTO) :
            ResponseEntity<CompanyDTO>{
        return ResponseEntity.ok(companyService.updateCompany(id, dto))
    }
    @DeleteMapping("/{id}")
    fun deleteCompanyId(@PathVariable("id")id: Long): ResponseEntity<String> {
        return ResponseEntity.ok(companyService.deleteCompany(id))
    }
}