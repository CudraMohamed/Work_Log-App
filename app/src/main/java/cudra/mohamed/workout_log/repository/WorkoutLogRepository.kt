package cudra.mohamed.workout_log.repository

import androidx.lifecycle.LiveData
import cudra.mohamed.workout_log.WorkoutLog
import cudra.mohamed.workout_log.database.WorkoutDB
import cudra.mohamed.workout_log.models.WorkoutLogRecord
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class WorkoutLogRepository {
    val database=WorkoutDB.getDatabase(WorkoutLog.appContext)
    val workoutLogRecordDao=database.workoutLogRecordDao()

    suspend fun saveWorkoutLogRecord(workoutLogRecord: WorkoutLogRecord){
        withContext(Dispatchers.IO){
            workoutLogRecordDao.insertWorkoutLogRecord(workoutLogRecord)
        }
    }
    fun getTodaysWorkoutLogRecords(userId:String,currentDate:String):LiveData<List<WorkoutLogRecord>>{
        return workoutLogRecordDao.getWorkoutLogByUserId(userId, currentDate)
    }
}