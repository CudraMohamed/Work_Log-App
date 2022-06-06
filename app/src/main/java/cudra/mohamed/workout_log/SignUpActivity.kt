package cudra.mohamed.workout_log

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignUpActivity : AppCompatActivity() {
    lateinit var tilFirstName: TextInputLayout
    lateinit var etFirstName: TextInputEditText

    lateinit var tilLastName: TextInputLayout
    lateinit var etLastName: TextInputEditText

    lateinit var tilSignUpEmail: TextInputLayout
    lateinit var etSignUpEmail: TextInputEditText

    lateinit var tilSignUpPassword: TextInputLayout
    lateinit var etSignUpPassword: TextInputEditText

    lateinit var tilSignUpConfirm: TextInputLayout
    lateinit var etSignUpConfirm: TextInputEditText

    lateinit var btnSignUp: Button
    lateinit var tvSignIn: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        tilFirstName = findViewById(R.id.tilFirstName)
        etFirstName = findViewById(R.id.etFirstName)

        tilLastName = findViewById(R.id.tilLastName)
        etLastName = findViewById(R.id.etLastName)

        tilSignUpEmail = findViewById(R.id.tilSignUpEmail)
        etSignUpEmail = findViewById(R.id.etSignUpEmail)

        tilSignUpPassword = findViewById(R.id.tilSignUpPassword)
        etSignUpPassword = findViewById(R.id.etSignUpPassword)

        tilSignUpConfirm = findViewById(R.id.tilSignUpConfirm)
        etSignUpConfirm = findViewById(R.id.etSignUpConfirm)

        btnSignUp = findViewById(R.id.btnSignUp)
        tvSignIn = findViewById(R.id.tvSignIn)

        btnSignUp.setOnClickListener {
//            etSignUpPassword.equals(etSignUpConfirm)
//            return@setOnClickListener

            validateSignUp()
        }
        tvSignIn.setOnClickListener {
            val intent = Intent(this,LogInActivity::class.java)
            startActivity(intent)
        }
    }
    fun validateSignUp(){
        var firstname = etFirstName.text.toString()
        var lastname = etLastName.text.toString()
        var signupEmail = etSignUpEmail.text.toString()
        var signupPassword = etSignUpPassword.text.toString()
        var signUpConfirmP = etSignUpConfirm.text.toString()

        if (firstname.isBlank()){
            tilFirstName.error = getString(R.string.first_name)
        }
        if (lastname.isBlank()){
            tilLastName.error = getString(R.string.last_name)
        }
        if (signupEmail.isBlank()){
            tilSignUpEmail.error = getString(R.string.email_required)
        }
        if (signupPassword.isBlank()){
            tilSignUpPassword.error = getString(R.string.password_required)
        }
        if (signupPassword.length<8){
            tilSignUpPassword.error = getString(R.string.password_short)
        }
        if (signupPassword.length>16){
            tilSignUpPassword.error = getString(R.string.password_long)
        }
        if (signUpConfirmP.isBlank()){
            tilSignUpConfirm.error = getString(R.string.ConfirmP_required)
        }
        if (signUpConfirmP!=signupPassword){
            tilSignUpConfirm.error = getString(R.string.confirming_passwords)
        }
        else if (signUpConfirmP.length<8){
            tilSignUpConfirm.error = getString(R.string.confirmp_short)
        }
        else if (signUpConfirmP.length>16){
            tilSignUpConfirm.error = getString(R.string.confirmp_long)
        }
    }
}