package com.soramitsukhmer.contactmanagement.domain.model

import com.soramitsukhmer.contactmanagement.api.request.RequestCompanyStaffDTO
import com.soramitsukhmer.contactmanagement.api.request.RequestStaffDTO
import com.soramitsukhmer.contactmanagement.api.request.StaffDTO
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "staffs")
data class Staff(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id : Long = 0,
        @Column(name = "name")
        var name: String,
        @Column(name = "gender")
        var gender: String,
        @Column(name = "location")
        var location: String?,
        @Column(name = "position")
        var position: String?,
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

    fun toDto() = StaffDTO(
            id = id,
            name = name,
            position = position,
            company = company.toDto(),
            gender = gender,
            location = location,
            createdAt = createdAt,
            updatedAt = updatedAt
    )
    companion object{
        fun fromDTO(requestStaffDTO: RequestStaffDTO,company: Company) : Staff {
            return Staff(
                    name = requestStaffDTO.name,
                    position = requestStaffDTO.position,
                    gender = requestStaffDTO.gender,
                    location = requestStaffDTO.location
            ).apply {
                this.company = company
            }}

        fun fromDTO(requestCompanyStaffDTO: RequestCompanyStaffDTO,company: Company) : Staff {
            return Staff(
                    name = requestCompanyStaffDTO.name,
                    position = requestCompanyStaffDTO.position,
                    gender = requestCompanyStaffDTO.gender,
                    location = requestCompanyStaffDTO.location
            ).apply {
                this.company = company
            }}
        fun fromDTO(requestStaffDTO: RequestStaffDTO, origin: Staff,company: Company) : Staff {
            return Staff(
                    id = origin.id,
                    name = requestStaffDTO.name,
                    gender = requestStaffDTO.gender,
                    location = requestStaffDTO.location,
                    position = requestStaffDTO.position
            ).apply {
                this.company = company
            }
        }
    }
}