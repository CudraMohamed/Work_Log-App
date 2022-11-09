package cudra.mohamed.workout_log.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cudra.mohamed.workout_log.models.Exercise
import cudra.mohamed.workout_log.models.ExerciseCategory
import cudra.mohamed.workout_log.repository.ExerciseRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class ExerciseViewModel:ViewModel() {
    val exerciseRepository=ExerciseRepository()
    lateinit var exerciseCategoryLiveData:LiveData<List<ExerciseCategory>>
    lateinit var exerciseLiveData: LiveData<List<Exercise>>
    val errorLiveData=MutableLiveData<String?>()


    fun fetchExerciseCategories(accessToken:String){
        viewModelScope.launch {
            val response=exerciseRepository.fetchExerciseCategories(accessToken)
            if(!response.isSuccessful){
//                exerciseCategoryLiveData.postValue(response.body())
//            }else{
//                val errorMsg=response.errorBody()?.string()
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
    fun getDbExerciseCategories(){
        exerciseCategoryLiveData=exerciseRepository.getDbExerciseCategories()
    }

    fun fetchApiExercises(accessToken: String){
        viewModelScope.launch {
           exerciseRepository.fetchApiExercises(accessToken)
        }

    }

    fun getDbExercises(){
        exerciseLiveData=exerciseRepository.getDbExercises()
    }

    fun getExercisesByCategoryId(categoryId:String){
        exerciseLiveData=exerciseRepository.getExercisesByCategoryId(categoryId)
    }

    fun getExercisesByExerciseIds(exerciseIds:List<String>):LiveData<List<Exercise>>{
        return exerciseRepository.getExerciseByExerciseIds(exerciseIds)
    }
}