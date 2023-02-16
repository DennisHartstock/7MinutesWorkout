package com.commcode.a7minutesworkout

data class Exercise(
    val id: Int,
    val name: String,
    val image: Int,
    var isSelected: Boolean,
    var isCompleted: Boolean,
)

