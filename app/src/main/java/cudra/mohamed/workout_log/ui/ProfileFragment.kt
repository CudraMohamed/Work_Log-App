package cudra.mohamed.workout_log.ui

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import cudra.mohamed.workout_log.R
import cudra.mohamed.workout_log.databinding.FragmentPlanBinding
import cudra.mohamed.workout_log.databinding.FragmentProfileBinding
import cudra.mohamed.workout_log.util.Constants

class ProfileFragment : Fragment() {

    lateinit var binding: FragmentProfileBinding
    lateinit var sharedPref: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

//        sharedPref=context.getSharedPreferences(Constants.prefsFile, AppCompatActivity.MODE_PRIVATE)
        val ivLogout=view?.findViewById<ImageView>(R.id.ivLogout)
        ivLogout?.setOnClickListener{
            logout()
        }
        binding= FragmentProfileBinding.inflate(layoutInflater,container,false)
        return binding.root
//        return inflater.inflate(R.layout.fragment_profile, container, false)

    } //add profile ui
    fun logout(){
        sharedPref= requireContext().getSharedPreferences(Constants.prefsFile,MODE_PRIVATE)
        val editor= sharedPref.edit()
        editor.putString(Constants.accessToken,Constants.EMPTY_STRING)
        editor.putString(Constants.userId,Constants.EMPTY_STRING)
        editor.putString(Constants.profileId,Constants.EMPTY_STRING)
        editor.apply()
        startActivity(Intent(requireContext(),LogInActivity::class.java))
        requireActivity().finish()
    }
}