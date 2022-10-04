package com.nghaninn.thales.controller

import com.nghaninn.thales.service.DataService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/data")
class DataController (val dataService: DataService) {

    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun downloadAllData() = dataService.downloadAllData()

//    @GetMapping("/test")
//    fun getSecret() = dataService.getSecret()
}