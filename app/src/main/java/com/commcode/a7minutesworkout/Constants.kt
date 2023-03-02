package com.commcode.a7minutesworkout

object Constants {

    fun defaultExerciseList(): ArrayList<Exercise> {
        val exerciseList = ArrayList<Exercise>()
        val squats1 = Exercise(
            1,
            "Squats",
            image = R.drawable.ic_squat,
            isSelected = false,
            isCompleted = false
        )
        val pushUps1 = Exercise(
            2,
            "PushUps",
            image = R.drawable.ic_push_up,
            isSelected = false,
            isCompleted = false
        )
        val plank1 = Exercise(
            3,
            "Plank",
            image = R.drawable.ic_plank,
            isSelected = false,
            isCompleted = false
        )
        val squats2 = Exercise(
            4,
            "Squats",
            image = R.drawable.ic_squat,
            isSelected = false,
            isCompleted = false
        )
        val pushUps2 = Exercise(
            5,
            "PushUps",
            image = R.drawable.ic_push_up,
            isSelected = false,
            isCompleted = false
        )
        val plank2 = Exercise(
            6,
            "Plank",
            image = R.drawable.ic_plank,
            isSelected = false,
            isCompleted = false
        )
        val squats3 = Exercise(
            7,
            "Squats",
            image = R.drawable.ic_squat,
            isSelected = false,
            isCompleted = false
        )
        val pushUps3 = Exercise(
            8,
            "PushUps",
            image = R.drawable.ic_push_up,
            isSelected = false,
            isCompleted = false
        )
        val plank3 = Exercise(
            9,
            "Plank",
            image = R.drawable.ic_plank,
            isSelected = false,
            isCompleted = false
        )
        exerciseList.add(squats1)
        exerciseList.add(pushUps1)
        exerciseList.add(plank1)
        exerciseList.add(squats2)
        exerciseList.add(pushUps2)
        exerciseList.add(plank2)
        exerciseList.add(squats3)
        exerciseList.add(pushUps3)
        exerciseList.add(plank3)
        return exerciseList
    }
}