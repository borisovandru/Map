package com.borisov.map.domain.models

/**
 * @author Borisov Andrey on 26.06.2022
 **/
data class MarkersResult(
    val result: List<MarkerDomain>
) : DataResult
