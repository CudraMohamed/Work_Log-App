package cudra.mohamed.workout_log.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cudra.mohamed.workout_log.models.WorkoutPlan
import cudra.mohamed.workout_log.models.WorkoutPlanItem
import cudra.mohamed.workout_log.repository.WorkoutPlanRepository
import kotlinx.coroutines.launch
import java.util.*

class WorkoutPlanViewModel :ViewModel(){
    val workoutPlanRepository=WorkoutPlanRepository()
    lateinit var workoutPlanLiveData: LiveData<WorkoutPlan>
    var selectedExerciseIds= mutableListOf<String>()

    fun saveWorkoutPlan(workoutPlan: WorkoutPlan){
        viewModelScope.launch {
            workoutPlanRepository.saveWorkoutPlan(workoutPlan)
        }
    }

    fun getExistingWorkoutPlan(userId:String){
        workoutPlanLiveData=workoutPlanRepository.getworkoutPlanItemByUserId(userId)
    }

    fun createWorkoutPlanItem(dayNumber:Int,workoutPlanId: String){
        val workoutPlanItem=WorkoutPlanItem(
            workoutPlanItemId = UUID.randomUUID().toString(),
            workoutPlanId = workoutPlanId,
            day = dayNumber,
            exerciseId = selectedExerciseIds
        )
        viewModelScope.launch {
            workoutPlanRepository.saveWorkoutPlanItem(workoutPlanItem)
        }
    }

    fun getTodayWorkoutPlanItem(workoutPlanId:String,dayNumber:Int):LiveData<WorkoutPlanItem>{
        return workoutPlanRepository.getTodayWorkoutPlanItem(workoutPlanId,dayNumber )
    }
}