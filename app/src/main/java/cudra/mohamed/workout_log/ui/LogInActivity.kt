package cudra.mohamed.workout_log.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import cudra.mohamed.workout_log.R
import cudra.mohamed.workout_log.databinding.ActivityLogInBinding
import cudra.mohamed.workout_log.models.ApiInterface
import cudra.mohamed.workout_log.models.LoginRequest
import cudra.mohamed.workout_log.models.LoginResponse
import cudra.mohamed.workout_log.retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogInActivity : AppCompatActivity() {
    lateinit var binding: ActivityLogInBinding
    lateinit var sharedPref:SharedPreferences   //key value stored

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref=getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)

        binding.btnLogin.setOnClickListener {
            validateLogIn()
//            finish()
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
            error=true
        }
        else if (password.length<8){
            binding.tilPassword.error = getString(R.string.login_password_short)
            error=true
        }
        else if (password.length>16){
            binding.tilPassword.error = getString(R.string.login_password_long)
            error=true
        }
        if (!error){
            var loginRequest=LoginRequest(email, password)
            binding.pbLogin.visibility=View.VISIBLE
            makeLoginRequest(loginRequest)
//            startActivity(Intent(this, HomeActivity::class.java))    //allows the activity to proceed only after validation
        }
    }
    fun makeLoginRequest(loginRequest: LoginRequest){
        var apiClient=ApiClient.buildApiClient(ApiInterface::class.java)
        var request=apiClient.login(loginRequest)
        request.enqueue(object :Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {

                binding.pbLogin.visibility=View.GONE
                if (response.isSuccessful){

                    var loginResponse=response.body()
                    saveLoginDetails(loginResponse!!)

                    Toast.makeText(baseContext,response.body()?.message,Toast.LENGTH_LONG).show()
                    startActivity(Intent(baseContext,HomeActivity::class.java))
                    finish()
                }else{
                    val error=response.errorBody()?.string()
                    Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                binding.pbLogin.visibility=View.GONE
                Toast.makeText(baseContext,t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
    fun saveLoginDetails(loginResponse: LoginResponse){
        val editor=sharedPref.edit()
        editor.putString("ACCESS_TOKEN",loginResponse.accessToken)
        editor.putString("USER_ID",loginResponse.userId)
        editor.putString("PROFILE_ID",loginResponse.profileId)
        editor.apply()
    }
}