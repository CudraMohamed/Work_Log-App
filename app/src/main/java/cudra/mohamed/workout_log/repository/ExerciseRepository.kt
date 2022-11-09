package cudra.mohamed.workout_log.repository

import androidx.lifecycle.LiveData
import cudra.mohamed.workout_log.WorkoutLog
import cudra.mohamed.workout_log.api.ApiClient
import cudra.mohamed.workout_log.api.ApiInterface
import cudra.mohamed.workout_log.database.WorkoutDB
import cudra.mohamed.workout_log.models.Exercise
import cudra.mohamed.workout_log.models.ExerciseCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ExerciseRepository {
    val apiClient=ApiClient.buildApiClient(ApiInterface::class.java)
    val database=WorkoutDB.getDatabase(WorkoutLog.appContext) //an instance of our db
    val exerciseCategoryDao=database.exerciseCategoryDao()
    val exerciseDao=database.exerciseDao()

    suspend fun fetchExerciseCategories(accessToken:String):Response<List<ExerciseCategory>>{
        return withContext(Dispatchers.IO){
            var response=apiClient.fetchExerciseCategories(accessToken)
            if(response.isSuccessful){
                var categories=response.body()
                categories?.forEach { category->
                    exerciseCategoryDao.insertExerciseCategory(category)
                }
            }
            response
        }
    }

    fun getDbExerciseCategories():LiveData<List<ExerciseCategory>>{
        return exerciseCategoryDao.getAllExercises()
    }

    suspend fun fetchApiExercises(accessToken: String){
        withContext(Dispatchers.IO){
            val response=apiClient.fetchExercises(accessToken)
            if(response.isSuccessful){
                val exercises=response.body()
                exercises?.forEach { exercise ->
                    exerciseDao.insertExercises(exercise)
                }
            }
            return@withContext response
        }
    }

    fun getDbExercises():LiveData<List<Exercise>>{
        return exerciseDao.getExercises()
    }

    fun getExercisesByCategoryId(categoryId:String):LiveData<List<Exercise>>{
        return exerciseDao.getExercisesByCategoryId(categoryId)
    }

    fun getExerciseByExerciseIds(exerciseIds:List<String>):LiveData<List<Exercise>>{
        return exerciseDao.getTodayExercisesByIds(exerciseIds)
    }
}