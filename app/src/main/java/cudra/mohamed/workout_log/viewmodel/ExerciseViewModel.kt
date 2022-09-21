package cudra.mohamed.workout_log.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cudra.mohamed.workout_log.models.ExerciseCategory
import cudra.mohamed.workout_log.repository.ExerciseRepository
import kotlinx.coroutines.launch

class ExerciseViewModel:ViewModel() {
    val exerciseRepository=ExerciseRepository()
    val exerciseCategoryLiveData=MutableLiveData<List<ExerciseCategory>>()
    val errorLiveData=MutableLiveData<String?>()

    fun fetchExerciseCategories(accessToken:String){
        viewModelScope.launch {
            val response=exerciseRepository.fetchExerciseCategories(accessToken)
            if(response.isSuccessful){
                exerciseCategoryLiveData.postValue(response.body())
            }else{
                val errorMsg=response.errorBody()?.string()
                errorLiveData.postValue(errorMsg)
            }
        }
    }
}