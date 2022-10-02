package com.nghaninn.thales.controller

import com.nghaninn.thales.dto.AirportDTO
import com.nghaninn.thales.dto.AirportTopWaypointDTO
import com.nghaninn.thales.service.AirportService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/airport")
class AirportController (
    val airportService: AirportService
) {

    @GetMapping("/topWaypoint")
    fun listTopWaypoints(
        @RequestParam("airport_icaos", required = false) airportIcaos: List<String>?,
        @RequestParam("top", required=false) top: Int?
    ): List<AirportTopWaypointDTO> = airportService.listTopWaypoints(airportIcaos, top)
}