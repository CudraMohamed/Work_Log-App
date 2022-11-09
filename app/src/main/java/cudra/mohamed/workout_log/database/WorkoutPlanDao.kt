package cudra.mohamed.workout_log.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cudra.mohamed.workout_log.models.WorkoutPlan

@Dao
interface WorkoutPlanDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorkoutPlan(workoutPlan: WorkoutPlan)

    @Query("SELECT * FROM workoutplan WHERE userId=:userId")
    fun getWorkoutPlanByUserId(userId:String):LiveData<WorkoutPlan>
}