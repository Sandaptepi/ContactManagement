package com.soramitsukhmer.contactmanagement.service.validation

import com.soramitsukhmer.contactmanagement.repository.CompanyRepository
import org.springframework.stereotype.Service

@Service
class CompanyValidationService(
        val companyRepository: CompanyRepository
) {
    fun validateUniquePhoneAndName(companyId: Long?, phone: String,name: String){
        companyId?.let {
            if(companyRepository.existsCompanyByPhoneAndNameAndIdIsNot(phone, name ,companyId)){
                throw RuntimeException("[Phone:$phone] or [Name:$name] is already existed.")
            }
            return
        }
        if(companyRepository.existsCompanyByPhoneOrName(phone,name)){
            throw RuntimeException("[Phone:$phone] or [Name:$name] must be unique")
        }
    }

//    fun validateUniquePhone(companyId: Long?, phone: String, name: String){
//        when(companyId) {
//            null -> if (companyRepository.existsCompanyByPhoneOrName(phone,name))
//                throw RecordAlreadyExistException("[Phone:$phone] or [Name:$name] is already existed.")
//            else -> if (companyRepository.existsCompanyByPhoneAndNameAndIdIsNot(phone,name,companyId))
//                throw RecordAlreadyExistException("[Phone:$phone] or [Name:$name] must be unique")
//        }
//    }
}

