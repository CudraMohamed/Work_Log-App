package cudra.mohamed.workout_log

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import cudra.mohamed.workout_log.databinding.ActivityLogInBinding
import cudra.mohamed.workout_log.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnSignUp.setOnClickListener {
//            etSignUpPassword.equals(etSignUpConfirm)
//            return@setOnClickListener

            validateSignUp()
        }
        binding.tvSignIn.setOnClickListener {
            val intent = Intent(this,LogInActivity::class.java)
            startActivity(intent)
        }
    }
    fun validateSignUp(){
        var firstname = binding.etFirstName.text.toString()
        var lastname = binding.etLastName.text.toString()
        var signupEmail = binding.etSignUpEmail.text.toString()
        var signupPassword = binding.etSignUpPassword.text.toString()
        var signUpConfirmP = binding.etSignUpConfirm.text.toString()

        if (firstname.isBlank()){
            binding.tilFirstName.error = getString(R.string.first_name)
        }
        if (lastname.isBlank()){
            binding.tilLastName.error = getString(R.string.last_name)
        }
        if (signupEmail.isBlank()){
            binding.tilSignUpEmail.error = getString(R.string.email_required)
        }
        if (signupPassword.isBlank()){
            binding.tilSignUpPassword.error = getString(R.string.password_required)
        }
        if (signupPassword.length<8){
            binding.tilSignUpPassword.error = getString(R.string.password_short)
        }
        if (signupPassword.length>16){
            binding.tilSignUpPassword.error = getString(R.string.password_long)
        }
        if (signUpConfirmP.isBlank()){
            binding.tilSignUpConfirm.error = getString(R.string.ConfirmP_required)
        }
        if (signUpConfirmP!=signupPassword){
            binding.tilSignUpConfirm.error = getString(R.string.confirming_passwords)
        }
        else if (signUpConfirmP.length<8){
            binding.tilSignUpConfirm.error = getString(R.string.confirmp_short)
        }
        else if (signUpConfirmP.length>16){
            binding.tilSignUpConfirm.error = getString(R.string.confirmp_long)
        }
    }
}