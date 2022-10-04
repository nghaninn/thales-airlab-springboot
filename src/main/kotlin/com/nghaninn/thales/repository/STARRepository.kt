package com.nghaninn.thales.repository

import com.nghaninn.thales.entity.STAR
import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(path="star")
interface STARRepository : CrudRepository<STAR, String>{

}
