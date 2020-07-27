package com.soramitsukhmer.contactmanagement.api.controller

import com.soramitsukhmer.contactmanagement.api.request.FilterParamsStaffDTO
import com.soramitsukhmer.contactmanagement.api.request.PageResponse
import com.soramitsukhmer.contactmanagement.api.request.RequestStaffDTO
import com.soramitsukhmer.contactmanagement.api.request.StaffDTO
import com.soramitsukhmer.contactmanagement.repository.StaffRepository
import com.soramitsukhmer.contactmanagement.service.StaffService
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping(path = ["/api/v1/staffs"])
class StaffController (
        val staffService: StaffService,
        val staffRepository: StaffRepository
)
{
//    @GetMapping
//    fun listAllStaffs(@RequestParam(required = false,name = "companyId") companyId: Long?, pageable: Pageable):
//            ResponseEntity<PageResponse<StaffDTO>>{
//        return ResponseEntity.ok(staffService.listAllStaffs(companyId,pageable))
//    }
    @GetMapping
    fun listAllStaffs(@Valid filterParamsStaffDTO: FilterParamsStaffDTO?,
                      pageable: Pageable) : ResponseEntity<PageResponse<StaffDTO>>{
        return ResponseEntity.ok(staffService.listAllStaffs(filterParamsStaffDTO, pageable))
        }

    @GetMapping("/{id}")
    fun getStaff(@PathVariable id: Long): ResponseEntity<StaffDTO> {
        return  ResponseEntity.ok(staffService.getStaff(id))
    }
    @PostMapping
    fun addStaff(@Valid @RequestBody requestStaffDTO: RequestStaffDTO): ResponseEntity<StaffDTO> {
        return ResponseEntity.ok(staffService.addStaff(requestStaffDTO))
    }
    @PutMapping("/{id}")
    fun updateStaff(@PathVariable id: Long, @Valid @RequestBody requestStaffDTO: RequestStaffDTO):
            ResponseEntity<StaffDTO> {
        return ResponseEntity.ok(staffService.updateStaff(id,requestStaffDTO))
    }

    @DeleteMapping("/{id}")
    fun deleteStaffId(@PathVariable("id")id: Long):
            ResponseEntity<String> {
        staffRepository.deleteById(id)
        return ResponseEntity.ok(staffService.deleteStaff(id))
    }
//    @DeleteMapping("/{id}")
//    fun deleteStaffId(@RequestParam filterParamsStaffDTO: FilterParamsStaffDTO?, @PathVariable("id")id: Long,
//                      @RequestBody requestStaffDTO: RequestStaffDTO, pageable: Pageable):
//            ResponseEntity<PageResponse<StaffDTO>> {
//        staffRepository.deleteById(id)
//        return ResponseEntity.ok(staffService.listAllStaffs(filterParamsStaffDTO,pageable))
//    }
}