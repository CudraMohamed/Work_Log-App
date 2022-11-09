package cudra.mohamed.workout_log.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import cudra.mohamed.workout_log.R
import cudra.mohamed.workout_log.databinding.ActivityHomeBinding
import cudra.mohamed.workout_log.util.Constants
import cudra.mohamed.workout_log.viewmodel.ExerciseViewModel

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var sharedPrefs:SharedPreferences
    val exerciseViewModel:ExerciseViewModel by viewModels()

//    lateinit var token:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.tvLogout.setOnClickListener {
//            startActivity(Intent(this,LogInActivity::class.java))
//            finish()
//            logOutRequest()
//        }

//        castView()
        setupBottomNav()
//        sharedPrefs=getSharedPreferences(Constants.prefsFile, MODE_PRIVATE) //changes

//        val token = sharedPrefs.getString(Constants.accessToken,Constants.EMPTY_STRING) //changes

//        val token = sharedPrefs.getString(Constants.accessToken,Constants.EMPTY_STRING)
//        exerciseViewModel.fetchExerciseCategories(token!!)
//
//        exerciseViewModel.fetchExerciseCategories(token!!)
//        exerciseViewModel.fetchApiExercises(token)
        exerciseViewModel.getDbExerciseCategories()
        exerciseViewModel.getDbExercises()
    }

    override fun onResume() {
        super.onResume()
        exerciseViewModel.exerciseCategoryLiveData.observe(this, Observer { exerciseCategories->
            if (exerciseCategories.isEmpty()){
                exerciseViewModel.fetchExerciseCategories(getAccessToken()) //fetching data
            }
            Toast.makeText(this,"fetched ${exerciseCategories.size} categories",Toast.LENGTH_LONG).show()
        })
        exerciseViewModel.errorLiveData.observe(this, Observer { errorMsg->
            Toast.makeText(this,errorMsg,Toast.LENGTH_LONG).show()
        })
        exerciseViewModel.exerciseLiveData.observe(this, Observer { exercise ->
            if (exercise.isEmpty()) {
                exerciseViewModel.fetchApiExercises(getAccessToken())
            }
        })
    }

//    fun castView() {
//        binding.fcvHome
//        binding.bnvHome
//    }
    fun getAccessToken(): String {
        sharedPrefs = getSharedPreferences(Constants.prefsFile, MODE_PRIVATE)
        return sharedPrefs.getString(Constants.accessToken, "")!!
    }

    fun setupBottomNav() {
        binding.bnvHome.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.plan -> {
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fcvHome, PlanFragment())
                    transaction.commit()
                    true
                }
                R.id.track -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, TrackFragment()).commit()
                    true
                }
                R.id.profile -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, ProfileFragment()).commit()
                    true
                    
                }
                else -> false
            }
        }
    }
    fun logOutRequest(){
        sharedPrefs.edit().clear().commit()
        startActivity(Intent(this,LogInActivity::class.java))
        finish()
    }
}