package com.nghaninn.thales.repository

import com.nghaninn.thales.entity.STARWaypoint
import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(path="starwaypoint")
interface STARWaypointRepository : CrudRepository<STARWaypoint, Int>{

}
