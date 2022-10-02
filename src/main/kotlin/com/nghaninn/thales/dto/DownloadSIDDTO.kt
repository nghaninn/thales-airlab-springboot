package com.nghaninn.thales.dto

data class DownloadSIDDTO (
    var name: String,
    var airport: AirportDTO,
    var waypoints: List<WaypointDTO>
)