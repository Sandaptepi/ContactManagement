package com.soramitsukhmer.contactmanagement.api.controller

import com.soramitsukhmer.contactmanagement.api.request.*
import com.soramitsukhmer.contactmanagement.service.CompanyService
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@ControllerAdvice
@RestController
@RequestMapping(path = ["/api/v1/companies"])
class CompanyController(
        val companyService: CompanyService
){
    @GetMapping
    fun listAllCompanies(@Valid filterParamsCompanyDTO: FilterParamsCompanyDTO, pageable: Pageable)
            : Any{
        return ok(companyService.listAllCompanies(filterParamsCompanyDTO,pageable))
    }
    @GetMapping("/{id}")
    fun getCompany(@PathVariable id: Long) : Any{
        return ok(companyService.getCompany(id))
    }

//    @GetMapping("/save")
//    fun save(): String {
//
//        val PP = Location(1, "PP")
//        val SR = Location(2, "SR")
//        val BT = Location(3, "BT")
//
//        val sora = Company(1, "Sora","2345678","sdfghjk","cvghj",listOf(PP,SR,BT))
//        val mitsu = Company(2, "Mitsu","2345678","sdfghjk","cvghj",listOf(SR,BT))
//
//        companyRepository.saveAll(listOf(sora, mitsu))
//
//        return "Done!"
//    }

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
    private fun <T> ok(data: T) : T = data
}