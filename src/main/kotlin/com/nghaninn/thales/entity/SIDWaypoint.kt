package com.nghaninn.thales.entity

import java.io.Serializable
import javax.persistence.*


@Entity
@Table(name = "SIDWaypoint")
//@IdClass(SIDWaypointID::class)
data class SIDWaypoint(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int?,
    var SIDName: String,
    var waypointUid: String,
) : Serializable

//class SIDWaypointID (
//    var SIDName: String,
//    var waypointUid: String,
//): Serializable