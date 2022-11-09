package cudra.mohamed.workout_log.ui

import android.content.Context
import android.os.Bundle
import android.provider.SyncStateContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import cudra.mohamed.workout_log.R
import cudra.mohamed.workout_log.databinding.FragmentPlanBinding
import cudra.mohamed.workout_log.models.Exercise
import cudra.mohamed.workout_log.models.ExerciseCategory
import cudra.mohamed.workout_log.models.WorkoutPlan
import cudra.mohamed.workout_log.util.Constants
import cudra.mohamed.workout_log.util.Constants.Companion.userId
import cudra.mohamed.workout_log.viewmodel.ExerciseViewModel
import cudra.mohamed.workout_log.viewmodel.WorkoutPlanViewModel
import java.util.*


class PlanFragment : Fragment() {
    val exerciseViewModel:ExerciseViewModel by viewModels()
    val workoutPlanViewModel:WorkoutPlanViewModel by viewModels()
    var binding: FragmentPlanBinding?=null  //allows building after the activity has been destroyed
    lateinit var workoutPlanId:String

    val bind get() = binding!!  //assigned from a nullable variable



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentPlanBinding.inflate(inflater,container,false)
        return bind.root
    }

    override fun onResume() {
        super.onResume()
        setUpSpinners()
        exerciseViewModel.getDbExerciseCategories()
        setUpCategorySpinner()
        bind.btnAddItem.setOnClickListener {
            clickAddItem()
        }
        bind.btnSaveDay.setOnClickListener {
            clickSaveDay()
        }
        checkExistingWorkoutPlan()
    }

    fun setUpSpinners(){
        setUpDaySpinner()
    }
    fun setUpDaySpinner(){
        val days= listOf("Select Day","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday")
        val daysAdapter=ArrayAdapter<String>(requireContext(),android.R.layout.simple_spinner_item,days)
        daysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        bind.spDays.adapter=daysAdapter
    }


    fun setUpCategorySpinner(){
        exerciseViewModel.exerciseCategoryLiveData.observe(this, Observer { categories->
            val firstCategory=ExerciseCategory("Select Category","0")
            val displayCategories= mutableListOf(firstCategory)
            displayCategories.addAll(categories)
            val categoryAdapter=ExerciseCategoryAdapter(requireContext(),displayCategories)
            bind.spCategories.adapter=categoryAdapter
            
            bind.spCategories.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    p1: View?,
                    p2: Int,
                    p3: Long
                ) {
                    val selectedCategory=displayCategories.get(p2)
                    val categoryId=selectedCategory.categoryId
                    exerciseViewModel.getExercisesByCategoryId(categoryId)
                    setUpExerciseSpinner()  //seting up the exercise spinner every time the category changes
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        })
    }

    fun setUpExerciseSpinner(){
        exerciseViewModel.exerciseLiveData.observe(this, Observer { exercises->
            val firstExercise=Exercise("0","","","0","Selected Exercise")
            val displayExercises= mutableListOf(firstExercise)
            displayExercises.addAll(exercises)
            bind.spExercices.adapter=ExerciseAdapter(requireContext(),displayExercises)
            exerciseViewModel.getDbExercises()
        })
    }
    fun clickAddItem(){
        var error=false
        if(bind.spDays.selectedItemPosition==0){
            error=true
            Toast.makeText(requireContext(),"Select day",Toast.LENGTH_LONG).show()
        }
        if(bind.spCategories.selectedItemPosition==0){
            error=true
            Toast.makeText(requireContext(),"Select category",Toast.LENGTH_LONG).show()
        }
        if(bind.spExercices.selectedItemPosition==0){
            error=true
            Toast.makeText(requireContext(),"Select Exercise",Toast.LENGTH_LONG).show()
        }
        if (!error){
            val selectedExercise=bind.spExercices.selectedItem as Exercise
            workoutPlanViewModel.selectedExerciseIds.add(selectedExercise.exerciseId)
//            val day=bind.spDays.selectedItem.toString()
            bind.spExercices.setSelection(0)
            bind.spCategories.setSelection(0)
        }
    }

    fun checkExistingWorkoutPlan(){
        val prefs=requireActivity().getSharedPreferences(Constants.prefsFile, Context.MODE_PRIVATE)
        val userId=prefs.getString(Constants.userId,"").toString()
        workoutPlanViewModel.getExistingWorkoutPlan(userId)
        workoutPlanViewModel.workoutPlanLiveData.observe(this, Observer { workoutPlan->
            if (workoutPlan==null){
                val newWorkoutPlan=WorkoutPlan(UUID.randomUUID().toString(),userId)
                workoutPlanViewModel.saveWorkoutPlan(newWorkoutPlan)
            }else{
                workoutPlanId=workoutPlan.workoutPlanId
            }
        })
    }
    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }

    fun getDayNumber(day:String):Int?{
        val dayMap= hashMapOf<String,Int>()
        dayMap.put("Monday",1)
        dayMap.put("Tuesday",2)
        dayMap.put("Wednesday",3)
        dayMap.put("Thursday",4)
        dayMap.put("Friday",5)
        dayMap.put("Saturday",6)
        dayMap.put("Sunday",7)
        return dayMap.get(day)?:-1
    }

    fun clickSaveDay(){
        if (bind.spDays.selectedItemPosition == 0){
            Toast.makeText(requireContext(),"Select Day",Toast.LENGTH_LONG).show()
            return
        }
        val day=bind.spDays.selectedItem.toString() //the day that has been selected
        val dayNumber=getDayNumber(day)
        if(workoutPlanViewModel.selectedExerciseIds.isEmpty()){
            Toast.makeText(requireContext(),"Select Some Exercises for $day",Toast.LENGTH_LONG).show()
        }
        if(dayNumber!=null){
            workoutPlanViewModel.createWorkoutPlanItem(dayNumber,workoutPlanId)
        }
        bind.spDays.setSelection(0)
    }
}