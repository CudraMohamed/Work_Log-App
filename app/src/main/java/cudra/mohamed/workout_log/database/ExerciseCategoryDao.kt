package cudra.mohamed.workout_log.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cudra.mohamed.workout_log.models.Exercise
import cudra.mohamed.workout_log.models.ExerciseCategory
import retrofit2.http.GET

@Dao
interface ExerciseCategoryDao {
    @Insert(onConflict= OnConflictStrategy.REPLACE)
    fun insertExerciseCategory(category: ExerciseCategory)

    //Querying data
    @Query("SELECt * FROM ExerciseCategories")
    fun getAllExercises(): LiveData<List<ExerciseCategory>>

//    @Query("SELECT * FROM exercisecategories WHERE categoryId= :categoryId")
//    fun getExerciseById(categoryId:Int): LiveData<ExerciseCategory>
//     fun insertExerciseCategory(category: ExerciseCategory)
}