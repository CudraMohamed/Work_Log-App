package cudra.mohamed.workout_log.models

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/register")
    fun registerUser(@Body registerRequest: RegisterRequest):Call<RegisterResponse>

    @POST("/login")
    fun login(@Body loginRequest: LoginRequest):Call<LoginResponse>
}