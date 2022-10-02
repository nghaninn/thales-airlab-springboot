package com.nghaninn.thales.dto

import javax.persistence.Id

data class AirportDTO (
    var uid: String,
    var name: String,
    var icao: String,
    var lat: Float,
    var lng: Float,
    val alt: Int,
)