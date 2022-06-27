package com.borisov.map.ui

/**
 * @author Borisov Andrey on 27.06.2022
 **/
interface Screen {
    fun loading(isLoading: Boolean)
    fun showError(throwable: Throwable)
}