package cudra.mohamed.workout_log.api

import cudra.mohamed.workout_log.models.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {
    @POST("/register")
    fun registerUser(@Body registerRequest: RegisterRequest):Response<RegisterResponse>

    @POST("/login")
    suspend fun login(@Body loginRequest: LoginRequest):Response<LoginResponse>

    @POST("/profile")
    suspend fun userProfile(@Body profileRequest: ProfileRequest):Response<ProfileResponse>

    @GET("/exercise-categories")   //header- key,value pair of additional info sending on the request - they are also keys and values
    suspend fun fetchExerciseCategories(@Header("Authorization")accessToken:String): Response<List<ExerciseCategory>>   //key-authorization-value-bearer and space

    @GET("/exercises")
    suspend fun fetchExercises(@Header("Authorization")accessToken: String):Response<List<Exercise>>
}