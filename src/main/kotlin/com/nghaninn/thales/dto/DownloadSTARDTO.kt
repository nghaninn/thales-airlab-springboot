package com.nghaninn.thales.dto

import com.nghaninn.thales.entity.Waypoint

data class DownloadSTARDTO (
    var name: String,
    var airport: AirportDTO,
    var waypoints: List<WaypointDTO>
)