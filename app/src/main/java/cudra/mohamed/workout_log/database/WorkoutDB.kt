package cudra.mohamed.workout_log.database

import android.content.Context
import androidx.room.*
import cudra.mohamed.workout_log.models.*

@Database(
    entities = arrayOf(
        ExerciseCategory::class,
        Exercise::class,
        WorkoutPlan::class,
        WorkoutPlanItem::class,
        WorkoutLogRecord::class),
    version = 6)
@TypeConverters(Converters::class)
abstract class WorkoutDB: RoomDatabase() {
    abstract fun exerciseCategoryDao():ExerciseCategoryDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun workoutPlanDao(): WorkoutPlanDao
    abstract fun workoutPlanItemDao(): WorkoutPlanItemDao
    abstract fun workoutLogRecordDao():WorkoutLogRecordDao

    companion object{
        private var database:WorkoutDB?=null

        fun getDatabase(context: Context):WorkoutDB{
            if(database==null){
                database= Room
                    .databaseBuilder(context,WorkoutDB::class.java,"WorkoutDB")
                    .fallbackToDestructiveMigration()   //destroys the existing datadase
                    .build()
            }
            return database as WorkoutDB
        }
    }
}