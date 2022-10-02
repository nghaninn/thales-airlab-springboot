package com.nghaninn.thales.entity

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "STARWaypoint")
//@IdClass(STARWaypointID::class)
data class STARWaypoint (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int?,
    var STARName: String,
    var waypointUid: String
) : Serializable

//class STARWaypointID (
//    var STARUid: String,
//    var waypointUid: String
//): Serializable