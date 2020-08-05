package com.soramitsukhmer.contactmanagement.domain.model

import com.soramitsukhmer.contactmanagement.api.request.LocationDTO
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "company_location")
data class CompanyLocation(
        @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCompanyLocations")
        @SequenceGenerator(name = "seqCompanyLocations", sequenceName = "SEQ_COMPANIES_LOCATIONS", initialValue = 1)
        val id : Long = 0,
        @CreationTimestamp
        @Column(name = "created_at")
        var createdAt: LocalDateTime = LocalDateTime.now(),
        @UpdateTimestamp
        @Column(name = "updated_at")
        var updatedAt: LocalDateTime = LocalDateTime.now()
){
        @ManyToOne
        @JoinColumn(name = "company_id")
        lateinit var company: Company

        @ManyToOne
        @JoinColumn(name = "location_id")
        lateinit var locations: Location

        fun toLocationDTO() = LocationDTO(
                id = this.locations.id,
                name = this.locations.name
        )
}