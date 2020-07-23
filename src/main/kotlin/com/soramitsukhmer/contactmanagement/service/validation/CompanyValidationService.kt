package com.soramitsukhmer.contactmanagement.service.validation

import com.soramitsukhmer.contactmanagement.api.exception.RecordAlreadyExistException
import com.soramitsukhmer.contactmanagement.repository.CompanyRepository
import org.springframework.stereotype.Service

@Service
class CompanyValidationService(
        val companyRepository: CompanyRepository
) {
//    fun validateUniquePhone(companyId: Long?, phone: String){
//        companyId?.let {
//            if(companyRepository.existsCompanyByPhoneAndIdIsNot(phone, it)){
//                throw RuntimeException("[Phone:$phone] is already existed.")
//            }
//            return
//        }
//        if(companyRepository.existsCompanyByPhone(phone)){
//            throw RuntimeException("[Phone:$phone] must be unique")
//        }
//    }

    fun validateUniquePhone(companyId: Long?, phone: String, name: String){
        when(companyId) {
            null -> if (companyRepository.existsCompanyByPhoneOrName(phone,name))
                throw RecordAlreadyExistException("[Phone:$phone] or [Name:$name] is already existed.")
            else -> if (companyRepository.existsCompanyByPhoneAndNameAndIdIsNot(phone,name,companyId))
                throw RecordAlreadyExistException("[Phone:$phone] or [Name:$name] must be unique")
        }
    }

//    fun validateUniquePhone(companyId: Long?, phone: String){
//
//
//
//    }

}

