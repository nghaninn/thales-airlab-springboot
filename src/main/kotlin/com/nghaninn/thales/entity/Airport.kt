package com.nghaninn.thales.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "Airport")
data class Airport (
    @Id
    var uid: String,
    var name: String?,
    var icao: String?,
    var lat: Float?,
    var lng: Float?,
    val alt: Int?,
)