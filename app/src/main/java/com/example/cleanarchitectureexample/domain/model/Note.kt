package com.example.cleanarchitectureexample.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cleanarchitectureexample.presentation.theme.*

@Entity
data class Note(
    val title: String,
    val content: String,
    val timeStamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val noteColors = listOf(Pink, Purple, LightBlue, Lime, Yellow)
    }
}

class InvalidNoteException(message: String): Exception(message)

sealed class NoteOrder(val orderType: OrderType) {

    class Title(orderType: OrderType): NoteOrder(orderType)
    class Date(orderType: OrderType): NoteOrder(orderType)
    class Color(orderType: OrderType): NoteOrder(orderType)

    fun copy(orderType: OrderType): NoteOrder {
        return when(this) {
            is Title -> Title(orderType)
            is Date -> Date(orderType)
            is Color -> Color(orderType)
        }
    }
}

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}
