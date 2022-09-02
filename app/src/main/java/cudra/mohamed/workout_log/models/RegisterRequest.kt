package cudra.mohamed.workout_log.models

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("first_name") var firstname:String,
    @SerializedName("last_name")var lastname:String,
    var email:String,
    @SerializedName("phone_number")var phoneNumber:String,
    var password:String
)
