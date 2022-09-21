package cudra.mohamed.workout_log.repository

import cudra.mohamed.workout_log.api.ApiClient
import cudra.mohamed.workout_log.api.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExerciseRepository {
    val apiClient=ApiClient.buildApiClient(ApiInterface::class.java)
    suspend fun fetchExerciseCategories(accessToken:String)= withContext(Dispatchers.IO){
        return@withContext apiClient.fetchExerciseCategories(accessToken)
    }
}