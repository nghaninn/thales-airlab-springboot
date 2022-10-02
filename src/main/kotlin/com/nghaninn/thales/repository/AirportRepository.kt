package com.nghaninn.thales.repository

import com.nghaninn.thales.entity.Airport
import com.nghaninn.thales.entity.SID
import org.springframework.data.repository.CrudRepository

interface  AirportRepository : CrudRepository<Airport, String>{

}
