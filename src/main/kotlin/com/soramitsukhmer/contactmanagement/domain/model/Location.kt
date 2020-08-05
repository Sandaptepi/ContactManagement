package com.soramitsukhmer.contactmanagement.domain.model

import com.soramitsukhmer.contactmanagement.api.request.LocationDTO
import com.soramitsukhmer.contactmanagement.api.request.RequestLocationDTO
import javax.persistence.*

@Entity
@Table(name="locations")
data class Location(
        @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqLocations")
        @SequenceGenerator(name = "seqLocations", sequenceName = "SEQ_LOCATIONS", initialValue = 1)
        val id : Long = 0,
        @Column(name = "name")
        var name: String
){
        @ManyToMany(cascade = [CascadeType.ALL])
        @JoinTable(name="company_location", joinColumns = [JoinColumn(name="company_id")],
                inverseJoinColumns = [JoinColumn(name="location_id")])
        lateinit var companies: List<Company>

        fun toDTO()=LocationDTO(
                id = this.id,
                name = this.name
        )
        fun updateLocation(dto: RequestLocationDTO) : Location
        {
                return this.apply { name = dto.name }
        }

        companion object {
                fun fromDto(dto: LocationDTO): Location {
                        return Location(
                                name = dto.name
                        )
                }
        }
}