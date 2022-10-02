package com.nghaninn.thales.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "STAR")
data class STAR (
    @Id
    var name: String,
    var airportUID: String
)