package cudra.mohamed.workout_log.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cudra.mohamed.workout_log.databinding.RowExerciseNameBinding
import cudra.mohamed.workout_log.models.Exercise

class TrackAdapter (val exerciseList:List<Exercise>,val logWorkout: LogWorkout):RecyclerView.Adapter<ExerciseViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val binding=RowExerciseNameBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  ExerciseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val currentExercise=exerciseList.get(position)
        holder.binding.tvExerciseName.text=currentExercise.exerciseName
        holder.binding.cbSet1.setOnClickListener {
            val weight=holder.binding.etWeight1.text.toString()
            val reps=holder.binding.etRep1.text.toString()
            logWorkout.onClickDone(set=1,weight=weight.toInt(), reps = reps.toInt(),currentExercise.exerciseId)
        }
        holder.binding.cbSet2.setOnClickListener {
            val weight=holder.binding.etWeight2.text.toString()
            val reps=holder.binding.etReps2.text.toString()
            logWorkout.onClickDone(set=1,weight=weight.toInt(), reps = reps.toInt(),currentExercise.exerciseId)
        }
        holder.binding.cbSet3.setOnClickListener {
            val weight=holder.binding.etWeight3.text.toString()
            val reps=holder.binding.etReps3.text.toString()
            logWorkout.onClickDone(set=1,weight=weight.toInt(), reps = reps.toInt(),currentExercise.exerciseId)
        }
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }
}
class ExerciseViewHolder(val binding:RowExerciseNameBinding):RecyclerView.ViewHolder(binding.root)

interface LogWorkout{
    fun onClickDone(set:Int,weight:Int,reps:Int,exerciseId:String)
}