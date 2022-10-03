package com.nghaninn.thales.service

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nghaninn.thales.dto.AirportDTO
import com.nghaninn.thales.dto.DownloadSIDDTO
import com.nghaninn.thales.dto.DownloadSTARDTO
import com.nghaninn.thales.dto.WaypointDTO
import com.nghaninn.thales.entity.*
import com.nghaninn.thales.repository.*
import okhttp3.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.IOException

@Service
class DataService(
    val airportRepository: AirportRepository,
    val waypointRepository: WaypointRepository,
    val sidRepository: SIDRepository,
    val sidWaypointRepository: SIDWaypointRepository,
    val starRepository: STARRepository,
    val starWaypointRepository: STARWaypointRepository,
) {

    private val client = OkHttpClient()

//    @Value("\${apiURL}")
//    lateinit var apiURL: String
//    @Value("\${apiKey}")
//    lateinit var apiKey: String

    var apiURL: String = System.getenv("thales_apiURL") ?: "default_value"
    var apiKey: String = System.getenv("thales_apiKey") ?: "default_value"

    fun run(url: String, method: String = "GET", body: RequestBody? = null, then: (String?) -> Unit) {
        val request = Request.Builder()
            .method(method = method, body = body)
            .addHeader("api-key", apiKey)
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
//                println(response.body?.string())
                then(response.body?.string())
            }
        })
    }

    fun downloadAllData() {
        // Delete all data in db.
        starWaypointRepository.deleteAll()
        sidWaypointRepository.deleteAll()
        starRepository.deleteAll()
        sidRepository.deleteAll()
        waypointRepository.deleteAll()
        airportRepository.deleteAll()

        // download Waypoint
        run("$apiURL/airac/waypoints") {
//            println("WAYPOINT $it")
            val gson = Gson()
            val listWaypointDTO = gson.fromJson<List<WaypointDTO>>(it, object :TypeToken<List<WaypointDTO>>(){}.type)
//            println(listWaypointDTO)

            val listWaypointEntity = listWaypointDTO.map {
                Waypoint(it.uid, it.name, it.lat, it.lng)
            }

            waypointRepository.saveAll(listWaypointEntity)
        }

        // download Airport
        lateinit var listAirportDTO: List<AirportDTO>
        run("$apiURL/airac/airports") {
//            println("AIRPORT $it")
            val gson = Gson()
            listAirportDTO = gson.fromJson<List<AirportDTO>>(it, object :TypeToken<List<AirportDTO>>(){}.type)
//            println(listAirportDTO)

            val listAirportEntity = listAirportDTO.map {
                Airport(it.uid, it.name, it.icao, it.lat, it.lng, it.alt)
            }

            airportRepository.saveAll(listAirportEntity)

            listAirportDTO.forEach {
                run("$apiURL/airac/sids/airport/${it.icao}") {
//                    println("SID $it")
//                    val gson = Gson()
                    val listDownloadSIDDTO = gson.fromJson<List<DownloadSIDDTO>>(it, object :TypeToken<List<DownloadSIDDTO>>(){}.type)
//                    println(listDownloadSIDDTO)

                    val listSIDEntity = listDownloadSIDDTO.map {
                        SID(it.name, it.airport.uid)
                    }
                    val listSIDWaypointEntity = listDownloadSIDDTO.flatMap { outerList ->
                        outerList.waypoints.map {wp->
                            SIDWaypoint(null, outerList.name, wp.uid)
                        }
                    }
//                    println("listSIDWaypointEntry $listSIDWaypointEntry")

                    sidRepository.saveAll(listSIDEntity)
                    sidWaypointRepository.saveAll(listSIDWaypointEntity)
                }

                run("$apiURL/airac/stars/airport/${it.icao}") {
//                    println("STAR $it")
//                    val gson = Gson()
                    val listDownloadSTARDTO = gson.fromJson<List<DownloadSTARDTO>>(it, object: TypeToken<List<DownloadSTARDTO>>() {}.type)
//                    println(listDownloadSTARDTO)

                    val listSTAREntity = listDownloadSTARDTO.map {
                        STAR(it.name, it.airport.uid)
                    }
                    val listSTARWaypointEntity = listDownloadSTARDTO.flatMap { outerList ->
                        outerList.waypoints.map {
                            STARWaypoint(null, outerList.name, it.uid)
                        }
                    }

                    starRepository.saveAll(listSTAREntity)
                    starWaypointRepository.saveAll(listSTARWaypointEntity)
                }
            }
        }
    }
}
