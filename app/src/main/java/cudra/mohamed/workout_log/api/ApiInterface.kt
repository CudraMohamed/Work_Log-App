package cudra.mohamed.workout_log.api

import cudra.mohamed.workout_log.models.LoginRequest
import cudra.mohamed.workout_log.models.LoginResponse
import cudra.mohamed.workout_log.models.RegisterRequest
import cudra.mohamed.workout_log.models.RegisterResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/register")
    fun registerUser(@Body registerRequest: RegisterRequest):Response<RegisterResponse>

    @POST("/login")
    suspend fun login(@Body loginRequest: LoginRequest):Response<LoginResponse>
}