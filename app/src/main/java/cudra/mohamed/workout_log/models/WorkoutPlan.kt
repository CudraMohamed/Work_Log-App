package cudra.mohamed.workout_log.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "WorkoutPlan")
data class WorkoutPlan (
    @PrimaryKey var workoutPlanId:String,
    var userId:String
    )