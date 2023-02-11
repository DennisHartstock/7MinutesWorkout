package com.commcode.a7minutesworkout

object Constants {

    fun defaultExerciseList(): ArrayList<Exercise> {
        val exerciseList = ArrayList<Exercise>()
        val squats = Exercise(
            1,
            "SQUATS",
            R.drawable.ic_squat,
            isSelected = false,
            isCompleted = false
        )
        val pushUps = Exercise(
            2,
            "PUSH-UPS",
            R.drawable.ic_push_up,
            isSelected = false,
            isCompleted = false
        )
        val plank = Exercise(
            3,
            "PLANK",
            R.drawable.ic_plank,
            isSelected = false,
            isCompleted = false
        )
        exerciseList.add(squats)
        exerciseList.add(pushUps)
        exerciseList.add(plank)
        return exerciseList
    }
}