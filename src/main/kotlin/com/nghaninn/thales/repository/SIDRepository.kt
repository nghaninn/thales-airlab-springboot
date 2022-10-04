package com.nghaninn.thales.repository

import com.nghaninn.thales.entity.SID
import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(path="sid")
interface  SIDRepository : CrudRepository<SID, String>{

}
