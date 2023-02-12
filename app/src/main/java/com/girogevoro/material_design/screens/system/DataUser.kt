package com.girogevoro.material_design.screens.system

sealed class DataUser {
    data class NoteUser(val id: Int, val title: String, val description: String) : DataUser()
    data class NoteTitle(val id: Int, val title: String) : DataUser()
}
