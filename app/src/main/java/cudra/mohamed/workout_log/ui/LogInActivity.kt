package cudra.mohamed.workout_log.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cudra.mohamed.workout_log.R
import cudra.mohamed.workout_log.databinding.ActivityLogInBinding

class LogInActivity : AppCompatActivity() {
    lateinit var binding: ActivityLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnLogin.setOnClickListener {
            validateLogIn()
            finish()
        }

        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
    fun validateLogIn(){
        var email = binding.etEmail.text.toString()
        var password = binding.etPassword.text.toString()
        var error=false

        if (email.isBlank()){
            binding.tilEmail.error = getString(R.string.email_required)
            error=true
        }
        else if (password.isBlank()){
            binding.tilPassword.error = getString(R.string.password_required)
        }
        else if (password.length<8){
            binding.tilPassword.error = getString(R.string.login_password_short)
        }
        else if (password.length>16){
            binding.tilPassword.error = getString(R.string.login_password_long)
        }
        if (!error){
            startActivity(Intent(this, HomeActivity::class.java))    //allows the activity to proceed only after validation
        }
    }
}