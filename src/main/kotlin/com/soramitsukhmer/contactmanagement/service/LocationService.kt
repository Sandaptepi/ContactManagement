package com.soramitsukhmer.contactmanagement.service

import com.soramitsukhmer.contactmanagement.api.request.LocationDTO
import com.soramitsukhmer.contactmanagement.repository.LocationRepository
import org.springframework.stereotype.Service

@Service
class LocationService(
        val locationRepository: LocationRepository
){
    fun listAllLocation() : List<LocationDTO>{
        return locationRepository.findAll().map { it.toDTO() }
    }
}