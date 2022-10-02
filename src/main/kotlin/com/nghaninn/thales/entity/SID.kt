package com.nghaninn.thales.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "SID")
data class SID (
    @Id
    var name: String,
    var airportUID: String,
)