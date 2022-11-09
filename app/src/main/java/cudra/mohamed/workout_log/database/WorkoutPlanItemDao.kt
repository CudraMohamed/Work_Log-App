package cudra.mohamed.workout_log.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cudra.mohamed.workout_log.models.WorkoutPlanItem

@Dao
interface WorkoutPlanItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorkoutPlanItem(workOutPlanItem: WorkoutPlanItem)

    @Query("SELECT * FROM workoutplanitem WHERE workoutPlanId=:workouPlanId AND day=:dayNumber")
    fun getTodayWorkoutPlanItem(workouPlanId:String, dayNumber:Int): LiveData<WorkoutPlanItem>
}