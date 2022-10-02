package com.nghaninn.thales.service

import com.nghaninn.thales.dto.AirportTopWaypointDTO
import com.nghaninn.thales.repository.AirportCustomRepository
import org.springframework.stereotype.Service

@Service
class AirportService (
    val airportCustomRepository: AirportCustomRepository
) {
    fun listTopWaypoints(airportIcaos: List<String>?, top: Int? = 2): List<AirportTopWaypointDTO> {
        val airportTopWaypoint = airportCustomRepository.findTopWaypoint(airportIcaos, top)

        return airportTopWaypoint
    }

}
