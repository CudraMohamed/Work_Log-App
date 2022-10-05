package cudra.mohamed.workout_log.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import cudra.mohamed.workout_log.R
import cudra.mohamed.workout_log.databinding.ActivityLogInBinding
import cudra.mohamed.workout_log.api.ApiInterface
import cudra.mohamed.workout_log.models.LoginRequest
import cudra.mohamed.workout_log.models.LoginResponse
import cudra.mohamed.workout_log.api.ApiClient
import cudra.mohamed.workout_log.util.Constants
import cudra.mohamed.workout_log.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogInActivity : AppCompatActivity() {
    lateinit var binding: ActivityLogInBinding
    lateinit var sharedPref:SharedPreferences   //key value stored
    val userViewModel:UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref=getSharedPreferences(Constants.prefsFile, MODE_PRIVATE)

        binding.btnLogin.setOnClickListener {
            validateLogIn()
//            finish()
        }

        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        userViewModel.loginResponseLiveData.observe(this, Observer { loginResponse->
            saveLoginDetails(loginResponse!!)
            Toast.makeText(baseContext,loginResponse?.message,Toast.LENGTH_LONG).show()
            startActivity(Intent(baseContext,HomeActivity::class.java))
            finish()
        })

        userViewModel.loginErrorLiveData.observe(this, Observer { error->
            Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
        })
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
//        else if (password.length<8){
//            binding.tilPassword.error = getString(R.string.login_password_short)
//            error=true
//        }
        else if (password.length>16){
            binding.tilPassword.error = getString(R.string.login_password_long)
            error=true
        }
        if (!error){
            var loginRequest=LoginRequest(email, password)
            binding.pbLogin.visibility=View.VISIBLE
            userViewModel.loginUser(loginRequest)
//            makeLoginRequest(loginRequest)

        }
    }
//    fun makeLoginRequest(loginRequest: LoginRequest){
//        var apiClient=ApiClient.buildApiClient(ApiInterface::class.java)
//        var request=apiClient.login(loginRequest)
//        request.enqueue(object :Callback<LoginResponse>{
//            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
//
//                binding.pbLogin.visibility=View.GONE
//                if (response.isSuccessful){
//
//                    var loginResponse=response.body()
//                    saveLoginDetails(loginResponse!!)
//
//                   //removed the toast and take it to the on response
//                }else{
//                    val error=response.errorBody()?.string()
//                   //remove toast and copy in the error live data
//                }
//            }
//
//            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                binding.pbLogin.visibility=View.GONE
//                Toast.makeText(baseContext,t.message, Toast.LENGTH_LONG).show()
//            }
//        })
//    }
    fun saveLoginDetails(loginResponse: LoginResponse){
        val editor=sharedPref.edit()
        val token="Bearer ${loginResponse.accessToken}" //
        editor.putString(Constants.accessToken,token)
        editor.putString(Constants.userId,loginResponse.userId)
        editor.putString(Constants.profileId,loginResponse.profileId)
        editor.apply()
    }
}