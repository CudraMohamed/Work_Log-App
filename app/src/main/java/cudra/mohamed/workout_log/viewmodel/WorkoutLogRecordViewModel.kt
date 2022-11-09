package cudra.mohamed.workout_log.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cudra.mohamed.workout_log.models.WorkoutLogRecord
import cudra.mohamed.workout_log.repository.WorkoutLogRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class WorkoutLogRecordViewModel:ViewModel() {
    val workoutLogRepository=WorkoutLogRepository()
    lateinit var todaysRecordLiveData:LiveData<List<WorkoutLogRecord>>

    fun saveWorkoutLogRecord(workoutLogRecord:WorkoutLogRecord){
        viewModelScope.launch {
            workoutLogRepository.saveWorkoutLogRecord(workoutLogRecord)
        }
    }
//    fun getExistingWorkoutLogRecord(){
//        todaysRecordLiveData
//    }
    fun getTodayWorkoutRecords(userId:String){
        todaysRecordLiveData=workoutLogRepository.getTodaysWorkoutLogRecords(userId, getCurrentDate())
    }

    fun getCurrentDate():String{
        val formartter=SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        return formartter.format(Date())
//        return formartter.parse(dateStr)!!
    }

}