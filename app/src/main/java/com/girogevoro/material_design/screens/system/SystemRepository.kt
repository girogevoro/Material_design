package com.girogevoro.material_design.screens.system

class SystemRepository(private val list: MutableList<DataUser>) {
    init {
        list.addAll(
            listOf(
                DataUser.Title(-3, "Title"),
                DataUser.Note(0, "One", "First"),
                DataUser.Note(1, "Two", "Second"),
                DataUser.Note(2, "Three", "Third")
            )
        )
    }

    fun add(position: Int, noteUser: DataUser) {
        list.add(position, noteUser)
    }

    fun move(positionOld: Int, positionNew: Int) {
        if (isRightPosition(positionOld) && isRightPosition(positionNew)) {
            list[positionOld].let {
                list.removeAt(positionOld)
                list.add(positionNew, it)
            }
        }
    }

    fun remove(position: Int) {
        if(isRightPosition(position)){
            list.removeAt(position)
        }
    }

    fun getList(): List<DataUser> {
        return list.toList()
    }

    private fun isRightPosition(position: Int): Boolean {
        return position >= 0 && list.size > position
    }

}
