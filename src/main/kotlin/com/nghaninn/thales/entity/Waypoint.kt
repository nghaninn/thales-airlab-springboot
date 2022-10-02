package com.nghaninn.thales.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "Waypoint")
data class Waypoint (
    @Id
    var uid: String,
    var name: String?,
    var lat: Float?,
    var lng: Float?,
)