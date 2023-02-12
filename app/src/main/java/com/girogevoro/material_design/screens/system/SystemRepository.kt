package com.girogevoro.material_design.screens.system

class SystemRepository(private val list: MutableList<DataUser>) {
    init {
        list.addAll(
            listOf(
                DataUser.NoteTitle(-3, "Title"),
                DataUser.NoteUser(0, "One", "First"),
                DataUser.NoteUser(1, "Two", "Second"),
                DataUser.NoteUser(2, "Three", "Third")
            )
        )
    }

    fun add(position: Int, noteUser: DataUser) {
        list.add(position, noteUser)
    }

    fun move(positionOld: Int, positionNew: Int) {
        list[positionOld].let {
            list.removeAt(positionOld)
            list.add(positionNew, it)
        }
    }

    fun remove(position: Int) {
        list.removeAt(position)
    }

    fun getList(): List<DataUser> {
        return list.toList()
    }


}
