package com.nghaninn.thales.controller

import com.nghaninn.thales.dto.AirportTopWaypointDTO
import com.nghaninn.thales.repository.*
import com.nghaninn.thales.util.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.util.UriComponentsBuilder
import java.util.stream.Stream


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class AirportControllerIntgTest {
    @Autowired
    lateinit var webTestClient: WebTestClient

    @Autowired
    lateinit var airportRepository: AirportRepository

    @Autowired
    lateinit var sidRepository: SIDRepository

    @Autowired
    lateinit var sidWaypointRepository: SIDWaypointRepository

    @Autowired
    lateinit var starRepository: STARRepository

    @Autowired
    lateinit var starWaypointRepository: STARWaypointRepository

    @Autowired
    lateinit var waypointRepository: WaypointRepository

    @BeforeEach
    fun setUp() {
        sidWaypointRepository.deleteAll()
        sidRepository.deleteAll()
        starWaypointRepository.deleteAll()
        starRepository.deleteAll()
        waypointRepository.deleteAll()
        airportRepository.deleteAll()

        airportRepository.saveAll(airport())
        waypointRepository.saveAll(waypoint())
        starRepository.saveAll(star())
        starWaypointRepository.saveAll(starWaypoint())
        sidRepository.saveAll(sid())
        sidWaypointRepository.saveAll(sidWaypoint())
    }

    @ParameterizedTest
    @MethodSource("topWaypointSID")
    fun listTopWaypointsSID(top: Int, airportIcaos: List<String>?, expectedResult: Int) {
        val uriBuilder = UriComponentsBuilder.fromUriString("/v1/airport/sid/topWaypoint")

        if (top > 0) {
            uriBuilder.queryParam("top", top)
        }

        if ((airportIcaos ?: listOf()).isNotEmpty()) {
            airportIcaos!!.map {
                uriBuilder.queryParam("airport_icaos", it)
            }
        }

        val airportTopWaypointDTOs = webTestClient
            .get()
            .uri(uriBuilder.toUriString())
            .exchange()
            .expectStatus().isOk
            .expectBodyList(AirportTopWaypointDTO::class.java)
            .returnResult()
            .responseBody

        println("airportTopWaypointDTOs : $airportTopWaypointDTOs")
        Assertions.assertEquals(expectedResult, airportTopWaypointDTOs!!.size)
    }

    @Test
    fun listTopWaypointsSID() {
        val uriBuilder = UriComponentsBuilder.fromUriString("/v1/airport/sid/topWaypoint")
            uriBuilder.queryParam("top", 3)
            uriBuilder.queryParam("airport_icaos", listOf("WSSS"))

        val airportTopWaypointDTOs = webTestClient
            .get()
            .uri(uriBuilder.toUriString())
            .exchange()
            .expectStatus().isOk
            .expectBodyList(AirportTopWaypointDTO::class.java)
            .returnResult()
            .responseBody

        println("airportTopWaypointDTOs : $airportTopWaypointDTOs")
        Assertions.assertEquals(3, airportTopWaypointDTOs!!.size)
    }

    companion object {
        @JvmStatic
        fun topWaypointSID(): Stream<Arguments> {
            return Stream.of(
                Arguments.arguments(3, null, 3),
                Arguments.arguments(3, listOf("WSSS"), 3),
                Arguments.arguments(3, listOf("W"), 0),
            )
        }
    }
}