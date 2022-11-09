package cudra.mohamed.workout_log.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cudra.mohamed.workout_log.models.Exercise

@Dao
interface ExerciseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExercises(exercise: Exercise)

    //Implementing the check if we have the data in the local database
    @Query("SELECT * FROM Exercises")
    fun getExercises():LiveData<List<Exercise>>

    @Query("SELECT * FROM Exercises WHERE categoryId=:categoryId")
    fun getExerciseByCategory(categoryId:String):LiveData<List<Exercise>>

    @Query("SELECT * FROM Exercises WHERE categoryId=:selectedCategoryId")
    fun getExercisesByCategoryId(selectedCategoryId:String): LiveData<List<Exercise>>

    @Query("SELECT * FROM Exercises WHERE exerciseId IN (:todayExerciseIds)")
    fun getTodayExercisesByIds(todayExerciseIds:List<String>):LiveData<List<Exercise>>

}