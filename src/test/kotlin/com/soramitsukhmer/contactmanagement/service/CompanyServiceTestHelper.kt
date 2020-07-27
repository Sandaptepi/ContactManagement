package com.soramitsukhmer.contactmanagement.service

import com.github.javafaker.Faker
import com.soramitsukhmer.contactmanagement.api.request.RequestCompanyDTO

object CompanyServiceTestHelper {
    var faker = Faker()
    val validCompanyReqDTO = RequestCompanyDTO(
            name = faker.funnyName().name(),
            phone = faker.phoneNumber().cellPhone(),
            webUrl = faker.internet().url(),
            statusId = 1
    )
}