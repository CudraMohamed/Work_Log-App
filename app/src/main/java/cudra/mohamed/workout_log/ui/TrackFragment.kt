package cudra.mohamed.workout_log.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import cudra.mohamed.workout_log.R
import cudra.mohamed.workout_log.databinding.FragmentTrackBinding
import cudra.mohamed.workout_log.models.WorkoutLogRecord
import cudra.mohamed.workout_log.models.WorkoutPlanItem
import cudra.mohamed.workout_log.util.Constants
import cudra.mohamed.workout_log.viewmodel.ExerciseViewModel
import cudra.mohamed.workout_log.viewmodel.WorkoutLogRecordViewModel
import cudra.mohamed.workout_log.viewmodel.WorkoutPlanViewModel
import java.time.LocalDate
import java.util.*

class TrackFragment : Fragment() {
    lateinit var binding: FragmentTrackBinding
    val workoutPlanViewModel:WorkoutPlanViewModel by viewModels()
    lateinit var prefs:SharedPreferences
    lateinit var userId:String
    val exerciseViewModel:ExerciseViewModel by viewModels()
    lateinit var workoutPlanItemId: String
    val workoutLogRecordViewModel:WorkoutLogRecordViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentTrackBinding.inflate(layoutInflater,container, false)
        return binding.root
    }

    @SuppressLint("NewApi")
    override fun onResume() {
        super.onResume()
        prefs=requireContext().getSharedPreferences(Constants.prefsFile, Context.MODE_PRIVATE)
        userId=prefs.getString(Constants.userId,Constants.EMPTY_STRING).toString()
        workoutPlanViewModel.getExistingWorkoutPlan(userId)
        workoutPlanViewModel.workoutPlanLiveData.observe(this, Observer {workoutPlan->
            val workoutPlanId=workoutPlan.workoutPlanId
            val dayNumber=LocalDate.now().dayOfWeek.value
            workoutPlanViewModel.getTodayWorkoutPlanItem(workoutPlanId, dayNumber).observe(this,
                Observer { workoutPlanItem->
                    if(workoutPlanItem!=null){
                        workoutPlanItemId=workoutPlanItem.workoutPlanItemId
                        val todayExerciseIds=workoutPlanItem.exerciseId
                        exerciseViewModel.getExercisesByExerciseIds(todayExerciseIds).observe(this,
                            Observer { exercises->
                                val adapter=TrackAdapter(exercises)
                                binding.rvTrack.layoutManager=LinearLayoutManager(requireContext())
                                binding.rvTrack.adapter=adapter
                            })
                    }else{
                        Toast.makeText(requireContext(),"No workout plan item found for today. Create one to proceed",Toast.LENGTH_LONG).show()
                    }

                })
        })

        

        override fun onClickDone(set:Int,weight:Int,reps:Int,exerciseId:String){
            val workoutLogRecord=WorkoutLogRecord(
            workoutLogId= UUID.randomUUID().toString(),
            date="",
            exerciseId=exerciseId,
            set=set,
            weight=weight,
            reps=reps,
            workoutPlanItemId=workoutPlanItemId,
            userId=userId)

            workoutPlanViewModel.saveWorkoutLogRecord(workoutLogRecord)
        }
    }
}