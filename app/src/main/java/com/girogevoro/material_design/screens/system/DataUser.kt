package com.girogevoro.material_design.screens.system

sealed class DataUser {
    abstract val type: Int

    data class Note(
        val id: Int, val title: String, val description: String,
        override val type: Int = TYPE_NOTE
    ) : DataUser()

    data class Title(
        val id: Int, val title: String,
        override val type: Int = TYPE_TITLE
    ) :
        DataUser()

    companion object {
        const val TYPE_NOTE = 0
        const val TYPE_TITLE = 1
    }
}
