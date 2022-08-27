package cudra.mohamed.workout_log.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, LogInActivity::class.java))
        finish()
//        val intent = Intent(this,LogInActivity::class.java)
//        startActivity(intent)
//        finish()
    }
}