package cudra.mohamed.workout_log

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LogInActivity : AppCompatActivity() {
    lateinit var btnLogin:Button
    lateinit var tilEmail:TextInputLayout
    lateinit var tilPassword:TextInputLayout
    lateinit var etEmail: TextInputEditText
    lateinit var etPassword:TextInputEditText
    lateinit var tvSignUp: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        btnLogin=findViewById(R.id.btnLogin)
        tilEmail=findViewById(R.id.tilEmail)
        tilPassword=findViewById(R.id.tilPassword)
        etEmail=findViewById(R.id.etEmail)
        etPassword=findViewById(R.id.etPassword)
        tvSignUp=findViewById(R.id.tvSignUp)

        btnLogin.setOnClickListener {
//            val intent=Intent(this,HomeActivity::class.java)
            startActivity(intent)
            validateLogIn()
        }

        tvSignUp.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
    }
    fun validateLogIn(){
        var email = etEmail.text.toString()
        var password = etPassword.text.toString()
        var error=false

        if (email.isBlank()){
            tilEmail.error = getString(R.string.email_required)
            error=true
        }
        if (password.isBlank()){
            tilPassword.error = getString(R.string.password_required)
        }
        else if (password.length<8){
            tilPassword.error = getString(R.string.login_password_short)
        }
        else if (password.length>16){
            tilPassword.error = getString(R.string.login_password_long)
        }
        if (!error){
            startActivity(Intent(this,HomeActivity::class.java))
        }
    }
}