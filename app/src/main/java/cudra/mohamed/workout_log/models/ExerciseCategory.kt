package cudra.mohamed.workout_log.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "ExerciseCategories")
data class ExerciseCategory(
    @PrimaryKey @SerializedName("category_name") var categoryName:String,
    @SerializedName("category_id") var categoryId:String
)
