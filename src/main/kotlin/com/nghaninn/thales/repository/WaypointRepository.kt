package com.nghaninn.thales.repository

import com.nghaninn.thales.entity.Waypoint
import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(path="waypoint")
interface WaypointRepository : CrudRepository<Waypoint, String> {

}
