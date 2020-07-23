package com.soramitsukhmer.contactmanagement.domain.model

import com.soramitsukhmer.contactmanagement.api.request.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "companies")
data class Company(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id : Long = 0,
        @Column(name = "name")
        var name: String,
        @Column(name = "phone")
        var phone: String,
        @Column(name = "web_url")
        var webUrl: String?,
        @Column(name = "private_pass_phrase")
        var privatePassPhrase: String,
        @CreationTimestamp
        @Column(name = "created_at")
        var createdAt: LocalDateTime = LocalDateTime.now(),
        @UpdateTimestamp
        @Column(name = "updated_at")
        var updatedAt: LocalDateTime = LocalDateTime.now()
){
        @ManyToOne
        @JoinColumn(name = "status_id")
        lateinit var status: Status

        fun toDTO() = CompanyDTO(
                id = this.id,
                name = name,
                phone = phone,
                webUrl = webUrl,
                status = this.status.toDTO(),
                createdAt = createdAt,
                updatedAt = updatedAt
        )

//        fun updateCompany(reqCompanyDTO: RequestCompanyDTO) : Company {
//                return this.apply {
//                        name = reqCompanyDTO.name
//                        phone = reqCompanyDTO.phone
//                        webUrl = reqCompanyDTO.webUrl
//                        status = reqCompanyDTO.statusId
//                        status = reqCompanyDTO.statusName
//                }
//        }

        companion object{
                fun fromReqDTO(reqCompanyDTO: RequestCompanyDTO, status: Status) : Company {
                        return Company(
                                name = reqCompanyDTO.name,
                                phone = reqCompanyDTO.phone,
                                webUrl = reqCompanyDTO.webUrl,
                                privatePassPhrase = "SORA"
                        ).apply { this.status = status }
                }
                fun fromReqDTO(dto: UpdateCompanyDTO, origin: Company, status: Status) : Company {
                        return Company(
                                id = origin.id,
                                name = dto.name,
                                phone = dto.phone,
                                webUrl = dto.webUrl,
                                privatePassPhrase = "SORA"
                        ).apply { this.status = status }
                }
        }
}