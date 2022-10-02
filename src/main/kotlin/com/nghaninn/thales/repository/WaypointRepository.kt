package com.nghaninn.thales.repository

import com.nghaninn.thales.entity.Waypoint
import org.springframework.data.repository.CrudRepository

interface WaypointRepository : CrudRepository<Waypoint, String> {

}
