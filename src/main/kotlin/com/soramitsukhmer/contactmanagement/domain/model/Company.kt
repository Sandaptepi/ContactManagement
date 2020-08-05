package com.soramitsukhmer.contactmanagement.domain.model

import com.soramitsukhmer.contactmanagement.api.request.CompanyDTO
import com.soramitsukhmer.contactmanagement.api.request.CompanyNameDTO
import com.soramitsukhmer.contactmanagement.api.request.RequestCompanyDTO
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Table(name = "companies")
data class Company(
        @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCompanies")
        @SequenceGenerator(name = "seqCompanies", sequenceName = "SEQ_COMPANIES", initialValue = 1)
        var id: Long = 0,
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

//        @ManyToMany(cascade = [CascadeType.ALL])
//        @JoinTable(name="company_location", joinColumns = [JoinColumn(name="company_id")],
//                inverseJoinColumns = [JoinColumn(name="location_id")])
//        lateinit var locations: List<Location>

//        @ManyToMany(mappedBy = "companies")
//        lateinit var location: List<Location>

        @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
        lateinit var companyLocations: List<CompanyLocation>

        fun toDTO() = CompanyDTO(
                id = id,
                name = name,
                phone = phone,
                webUrl = webUrl,
                status = status.toDTO(),
                createdAt = createdAt,
                updatedAt = updatedAt,
                locations = companyLocations.map { it.toLocationDTO() }
        )
        fun toDto() = CompanyNameDTO(
                name = this.name
        )
        fun updateCompany(reqCompanyDTO: RequestCompanyDTO, status: Status) : Company{
                return this.apply {
                        name = reqCompanyDTO.name
                        phone = reqCompanyDTO.phone
                        webUrl = reqCompanyDTO.webUrl
                        this.status = status
                }
        }
        companion object{
                fun fromReqDTO(reqCompanyDTO: RequestCompanyDTO, status: Status) : Company {
                        return Company(
                                name = reqCompanyDTO.name,
                                phone = reqCompanyDTO.phone,
                                webUrl = reqCompanyDTO.webUrl,
                                privatePassPhrase = "SORA"
                        ).apply { this.status = status }
                }
                fun fromReqDTO(dto: RequestCompanyDTO, origin: Company, status: Status, companyLocations: List<CompanyLocation>) : Company {
                        return Company(
                                id = origin.id,
                                name = dto.name,
                                phone = dto.phone,
                                webUrl = dto.webUrl,
                                privatePassPhrase = "SORA"
                        ).apply { this.status = status
                        this.companyLocations = companyLocations}
                }
        }
}