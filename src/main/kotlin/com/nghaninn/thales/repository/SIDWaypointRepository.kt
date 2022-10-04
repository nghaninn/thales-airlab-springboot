package com.nghaninn.thales.repository

import com.nghaninn.thales.entity.SIDWaypoint
import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(path="sidwaypoint")
interface SIDWaypointRepository: CrudRepository<SIDWaypoint, Int> {

}
