package cudra.mohamed.workout_log

import android.app.Application
import android.content.Context

class WorkoutLog:Application() {
    companion object{
        lateinit var appContext:Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext=applicationContext
    }
}