package com.soramitsukhmer.contactmanagement.domain.model

class ErrorCompanyException() : RuntimeException("Cannot delete company because it contains staffs")