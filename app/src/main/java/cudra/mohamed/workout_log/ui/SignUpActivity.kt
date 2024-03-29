package cudra.mohamed.workout_log.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import cudra.mohamed.workout_log.R
import cudra.mohamed.workout_log.databinding.ActivitySignUpBinding
import cudra.mohamed.workout_log.api.ApiInterface
import cudra.mohamed.workout_log.models.RegisterRequest
import cudra.mohamed.workout_log.models.RegisterResponse
import cudra.mohamed.workout_log.api.ApiClient
import cudra.mohamed.workout_log.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    val userViewModel:UserViewModel by viewModels()

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
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        userViewModel.registerResponseLiveData.observe(this, Observer { registerResponse-> //observe live data
            Toast.makeText(baseContext,registerResponse.message,Toast.LENGTH_LONG).show()
            startActivity(Intent(baseContext,LogInActivity::class.java))
        })
        userViewModel.registerErrorLiveData.observe(this, Observer { regError->
            Toast.makeText(baseContext,regError,Toast.LENGTH_LONG).show()
        })
    }

    fun validateSignUp(){
        val firstname = binding.etFirstName.text.toString()
        val lastname: String = binding.etLastName.text.toString()
        val email = binding.etSignUpEmail.text.toString()
        val phoneNumber=binding.etSignUpNumber.text.toString()
        val password = binding.etSignUpPassword.text.toString()
        val signUpConfirmP = binding.etSignUpConfirm.text.toString()

        var error=false

        if (firstname.isBlank()){
            error=true
            binding.tilFirstName.error = getString(R.string.first_name)
        }
        if (lastname.isBlank()){
            error=true
            binding.tilLastName.error = getString(R.string.last_name)
        }
        if (email.isBlank()){
            error=true
            binding.tilSignUpEmail.error = getString(R.string.email_required)
        }
        if (phoneNumber.isBlank()){
            error=true
            binding.etSignUpNumber.error=getString(R.string.phone_equired)
        }
        if (password.isBlank()){
            error=true
            binding.tilSignUpPassword.error = getString(R.string.password_required)
        }
        if (password.length<8){
            error=true
            binding.tilSignUpPassword.error = getString(R.string.password_short)
        }
        if (password.length>16){
            error=true
            binding.tilSignUpPassword.error = getString(R.string.password_long)
        }
        if (signUpConfirmP.isBlank()){
            error=true
            binding.tilSignUpConfirm.error = getString(R.string.ConfirmP_required)
        }
        if (signUpConfirmP!=password){
            error=true
            binding.tilSignUpConfirm.error = getString(R.string.confirming_passwords)
        }
        else if (signUpConfirmP.length<8){
            error=true
            binding.tilSignUpConfirm.error = getString(R.string.confirmp_short)
        }
        else if (signUpConfirmP.length>16){
            error=true
            binding.tilSignUpConfirm.error = getString(R.string.confirmp_long)
        }
        if(!error){
            val registerRequest=RegisterRequest(firstname,lastname,email,password,signUpConfirmP)
            userViewModel.registerUser(registerRequest) //invoke user viewmodel
//            makeRegisterRequest(registerRequest)
        }
    }
}