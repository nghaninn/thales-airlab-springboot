package com.nghaninn.thales.controller

import com.nghaninn.thales.service.DataService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/data")
class DataController (val dataService: DataService) {

    @GetMapping
    fun downloadAllData() = dataService.downloadAllData()
}