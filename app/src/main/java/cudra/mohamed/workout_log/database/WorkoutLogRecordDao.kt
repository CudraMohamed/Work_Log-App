package cudra.mohamed.workout_log.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cudra.mohamed.workout_log.models.WorkoutLogRecord
import java.util.*

@Dao
interface WorkoutLogRecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorkoutLogRecord(workoutLogRecord:WorkoutLogRecord)

    @Query("SELECT * FROM workoutlogrecord WHERE userId=:userId AND date >= :currentDate")
    fun getWorkoutLogByUserId(userId:String, currentDate:String): LiveData<List<WorkoutLogRecord>>

}