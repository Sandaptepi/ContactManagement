package com.soramitsukhmer.contactmanagement.domain.model

import com.soramitsukhmer.contactmanagement.api.request.StatusDTO
import javax.persistence.*

@Entity
@Table(name = "status")
data class Status (
    @Id
    val id : Long = 0,
    @Column(name = "name")
    var name: String
)
{
    fun toDTO() : StatusDTO = StatusDTO(
            name = this.name
    )

}