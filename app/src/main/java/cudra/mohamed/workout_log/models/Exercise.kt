package cudra.mohamed.workout_log.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Exercises")
data class Exercise(
    @PrimaryKey @SerializedName("exercise_id") var exerciseId:String,
    var description:String?,
    var image:String?,
    @SerializedName("category_id") var categoryId:String,
    @SerializedName("exercise_name") var exerciseName:String
)
