package com.borisov.map.domain.models

/**
 * @author Borisov Andrey on 26.06.2022
 **/
data class MarkerDomain(
    val markerId: Int = 0,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val visible: Boolean = true,
    val title: String = "",
    val description: String = "",
)
