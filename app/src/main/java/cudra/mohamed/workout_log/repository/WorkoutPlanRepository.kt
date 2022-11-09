package cudra.mohamed.workout_log.repository

import androidx.lifecycle.LiveData
import cudra.mohamed.workout_log.WorkoutLog
import cudra.mohamed.workout_log.database.WorkoutDB
import cudra.mohamed.workout_log.models.WorkoutPlan
import cudra.mohamed.workout_log.models.WorkoutPlanItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WorkoutPlanRepository {
    val database= WorkoutDB.getDatabase(WorkoutLog.appContext)
    val workoutPlanDao=database.workoutPlanDao()
    val workoutPlanItemDao=database.workoutPlanItemDao()

    suspend fun saveWorkoutPlan(workoutPlan: WorkoutPlan){
        withContext(Dispatchers.IO){
            workoutPlanDao.insertWorkoutPlan(workoutPlan)
        }
    }

    suspend fun saveWorkoutPlanItem(workoutPlanItem: WorkoutPlanItem){
        withContext(Dispatchers.IO){
            workoutPlanItemDao.insertWorkoutPlanItem(workoutPlanItem)
        }
    }

    fun getworkoutPlanItemByUserId(userId:String):LiveData<WorkoutPlan>{
        return workoutPlanDao.getWorkoutPlanByUserId(userId)
    }

    fun getTodayWorkoutPlanItem(workoutPlanId:String, dayNumber:Int)
    :LiveData<WorkoutPlanItem> {
        return workoutPlanItemDao.getTodayWorkoutPlanItem(workoutPlanId,dayNumber)
    }
}