package com.example.belorussianworkout

class Constants {
    companion object{
        fun defaultExerciseList(): ArrayList<ExerciseModule>{
            val exerciseList = ArrayList<ExerciseModule>()
            val crunches = ExerciseModule(
                1,
                "Crunches",
                R.drawable.ic_crunches,
                false,
                false)
            exerciseList.add(crunches)

            val reverseCrunches = ExerciseModule(
                2,
                "Reverse crunches",
                R.drawable.ic_reverse_crunches,
                false,
                false)
            exerciseList.add(reverseCrunches)

            val flutterKicks = ExerciseModule(
                3,
                "Flutter kicks",
                R.drawable.ic_flutter_kicks,
                false,
                false)
            exerciseList.add(flutterKicks)

            val sittingTwists = ExerciseModule(
                4,
                "Sitting Twists",
                R.drawable.ic_sitting_twists,
                false,
                false)
            exerciseList.add(sittingTwists)

            val kneeToElbow = ExerciseModule(
                5,
                "Knee to elbow",
                R.drawable.ic_knee_to_elbow,
                false,
                false)
            exerciseList.add(kneeToElbow)

            val halfWipers= ExerciseModule(
                6,
                "Half wipers",
                R.drawable.ic_half_wipers,
                false,
                false)
            exerciseList.add(halfWipers)

            return exerciseList
        }
    }
}