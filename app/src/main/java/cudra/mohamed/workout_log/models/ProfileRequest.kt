package cudra.mohamed.workout_log.models

data class ProfileRequest(
    var user_id:String,
    var sex:String,
    var date_of_birth:String,
    var weight:Int,
    var height:Int
)
