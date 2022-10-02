package com.nghaninn.thales.repository

import com.nghaninn.thales.dto.AirportTopWaypointDTO
import okhttp3.internal.toImmutableList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.query.Param
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import java.sql.ResultSet

@Repository
class AirportCustomRepository {

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    fun findTopWaypoint(@Param("icaos") airportIcaos: List<String>?, top: Int?): List<AirportTopWaypointDTO> {
        //Declare rowmapper to map DB records to collection of Beer entities:
        val rowMapper: RowMapper<AirportTopWaypointDTO> = RowMapper<AirportTopWaypointDTO> { resultSet: ResultSet, rowIndex: Int ->
            AirportTopWaypointDTO(resultSet.getString("airportUID"), resultSet.getString("waypoint_UID"), resultSet.getInt("counted"))
        }
        val where = if ((airportIcaos ?: emptyList()).isNotEmpty()) " WHERE Airport.UID IN ('"+ airportIcaos!!.joinToString("', '") + "') " else " "
         val limit = if ((top ?: 0) > 0) " LIMIT $top " else " "
        //Query records to print out:
        val results = jdbcTemplate.query("SELECT AirportUID, SIDWaypoint.Waypoint_UID, COUNT(SIDWaypoint.Waypoint_UID) as counted FROM SIDWaypoint\n" +
                "INNER JOIN SID ON SIDName = SID.NAME\n" +
                "INNER JOIN Airport ON SID.AirportUID = Airport.UID\n" + where +
                "GROUP BY Airport.UID, SIDWaypoint.Waypoint_UID\n" +
                "ORDER BY COUNT(SIDWaypoint.Waypoint_UID) DESC" + limit, rowMapper)

        return results.toImmutableList()
    }
}
