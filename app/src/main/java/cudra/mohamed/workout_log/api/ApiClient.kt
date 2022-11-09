package cudra.mohamed.workout_log.api

import com.chuckerteam.chucker.api.ChuckerInterceptor
import cudra.mohamed.workout_log.WorkoutLog
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(ChuckerInterceptor(WorkoutLog.appContext))
        .build()

    var retrofit=Retrofit.Builder()
        .baseUrl("http://192.81.215.35")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    fun<T> buildApiClient(apiInterface:Class<T>):T{
        return retrofit.create(apiInterface)
    }
}