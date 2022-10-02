package com.nghaninn.thales.dto

import javax.persistence.Column

data class AirportTopWaypointDTO (
//    var airport: AirportDTO,
//    var topWaypoint: TopWaypointDTO
    var airportUID: String,

    @Column(name="WAYPOINT_UID")
    var waypointUID: String,
    var counted: Int
)

data class TopWaypointDTO (
    var waypoint: WaypointDTO,
    var count: Int
)