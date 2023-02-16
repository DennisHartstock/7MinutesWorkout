package com.commcode.a7minutesworkout

object Constants {

    fun defaultExerciseList(): ArrayList<Exercise> {
        val exerciseList = ArrayList<Exercise>()
        val squats = Exercise(
            1,
            "SQUATS",
            image = R.drawable.ic_squat,
            isSelected = false,
            isCompleted = false
        )
        val pushUps = Exercise(
            2,
            "PUSH UPS",
            image = R.drawable.ic_push_up,
            isSelected = false,
            isCompleted = false
        )
        val plank = Exercise(
            3,
            "PLANK",
            image = R.drawable.ic_plank,
            isSelected = false,
            isCompleted = false
        )
        exerciseList.add(squats)
        exerciseList.add(pushUps)
        exerciseList.add(plank)
        return exerciseList
    }
}