package cudra.mohamed.workout_log.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cudra.mohamed.workout_log.models.LoginRequest
import cudra.mohamed.workout_log.models.LoginResponse
import cudra.mohamed.workout_log.models.RegisterRequest
import cudra.mohamed.workout_log.models.RegisterResponse
import cudra.mohamed.workout_log.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel :ViewModel(){
    val userRepository=UserRepository()
    var loginResponseLiveData=MutableLiveData<LoginResponse>()
    val loginErrorLiveData=MutableLiveData<String?>()
    var registerRequestLiveData=MutableLiveData<RegisterResponse>()
    val registerErrorLiveData= MutableLiveData<String?>()

    fun loginUser(loginRequest: LoginRequest){
        viewModelScope.launch {
            val response = userRepository.loginUser(loginRequest)
            if(response.isSuccessful){
                loginResponseLiveData.postValue(response.body())
            }
            else{
                val error = response.errorBody()?.string()
                loginErrorLiveData.postValue(error)
            }
        }
    }
    fun registerUser(registerRequest: RegisterRequest){
        viewModelScope.launch {
            val response=userRepository.makeUserRequest(registerRequest)
            if (response.isSuccessful){
                registerRequestLiveData.postValue(response.body())
            }
        }
    }

}