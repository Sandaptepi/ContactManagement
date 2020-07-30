package com.soramitsukhmer.contactmanagement.api.controller


import com.soramitsukhmer.contactmanagement.api.request.LocationDTO
import com.soramitsukhmer.contactmanagement.domain.model.Location
import com.soramitsukhmer.contactmanagement.repository.LocationRepository
import com.soramitsukhmer.contactmanagement.service.LocationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["/api/v1/locations"])
class LocationController(
        val locationRepository: LocationRepository,
        val locationService: LocationService
){

    @GetMapping
    fun listAllLocations() : ResponseEntity<List<LocationDTO>>{
        return ResponseEntity.ok(locationService.listAllLocation())
    }
    @PostMapping()
    fun createLocation(@RequestBody dto: LocationDTO): ResponseEntity<List<LocationDTO>> {
        val location = Location.fromDto(dto)
        locationRepository.save(location)
        return ResponseEntity.ok(locationService.listAllLocation())
    }
    @DeleteMapping("/{id}")
    fun deleteLocation(@PathVariable ("id") id: Long, @RequestBody dto: LocationDTO):
            ResponseEntity<List<LocationDTO>> {
        locationRepository.deleteById(id)
        return ResponseEntity.ok(locationService.listAllLocation())
    }
}