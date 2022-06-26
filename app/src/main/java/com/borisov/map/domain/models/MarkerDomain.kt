package com.borisov.map.domain.models

/**
 * @author Borisov Andrey on 26.06.2022
 **/
data class MarkerDomain(
    val markerId: Int,
    val latitude: Double,
    val longitude: Double,
    val visible: Boolean = true,
    val title: String,
    val description: String,
)
