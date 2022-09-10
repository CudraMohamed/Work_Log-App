package cudra.mohamed.workout_log.repository

import cudra.mohamed.workout_log.api.ApiClient
import cudra.mohamed.workout_log.api.ApiInterface
import cudra.mohamed.workout_log.models.LoginRequest
import cudra.mohamed.workout_log.models.RegisterRequest
import cudra.mohamed.workout_log.models.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {
    val apiClient=ApiClient.buildApiClient(ApiInterface::class.java)

    suspend fun loginUser(loginRequest: LoginRequest)= withContext(Dispatchers.IO){
        val response=apiClient.login(loginRequest)
        return@withContext response
    }
    suspend fun makeUserRequest(registerRequest: RegisterRequest) : Response<RegisterResponse>
    = withContext(Dispatchers.IO){
        val response=apiClient.registerUser(registerRequest)
        return@withContext response
    }
}