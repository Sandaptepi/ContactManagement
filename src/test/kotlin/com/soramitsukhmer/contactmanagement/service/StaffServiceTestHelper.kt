package com.soramitsukhmer.contactmanagement.service

import com.github.javafaker.Faker
import com.soramitsukhmer.contactmanagement.api.request.RequestStaffDTO

object StaffServiceTestHelper {
    var faker = Faker()
    val validStaffReqDTO = RequestStaffDTO(
            gender = "M",
            location = faker.address().cityName(),
            name = faker.name().fullName(),
            position = faker.job().position(),
            companyId = 1
    )
}