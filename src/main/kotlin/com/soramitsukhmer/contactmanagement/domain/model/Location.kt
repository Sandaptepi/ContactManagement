package com.soramitsukhmer.contactmanagement.domain.model

import com.soramitsukhmer.contactmanagement.api.request.LocationDTO
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

        @ManyToMany
        @JoinTable(name="company_location", joinColumns = [JoinColumn(name="location_id")],
                inverseJoinColumns = [JoinColumn(name="company_id")])
        lateinit var companyLocation: List<Company>

//        @ManyToMany(mappedBy = "companyLocation")
//        lateinit var companies: List<Company>

        fun toDTO(): LocationDTO = LocationDTO(
                id = this.id,
                name = this.name
        )

        companion object {
                fun fromDto(dto: LocationDTO): Location {
                        return Location(
                                name = dto.name
                        )
                }
        }
}