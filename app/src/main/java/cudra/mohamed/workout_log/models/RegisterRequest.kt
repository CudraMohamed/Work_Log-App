package cudra.mohamed.workout_log.models

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("firstName") var firstName:String,
    @SerializedName("last_name")var lastName:String,
    var email:String,
    @SerializedName("phone_number")var phoneNumber:String,
    var password:String
)
