package cudra.mohamed.workout_log.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {
    lateinit var sharedPref:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref=getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)
        val accessToken=sharedPref.getString("ACCESS_TOKEN","").toString()
        if (accessToken!!.isNotBlank()){
            startActivity(Intent(this,HomeActivity::class.java))
        }else{
            startActivity(Intent(this, LogInActivity::class.java))
        }
        finish()


//        val intent = Intent(this,LogInActivity::class.java)
//        startActivity(intent)
//        finish()
    }
}