package cudra.mohamed.workout_log.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ArrayAdapter
import android.widget.TextView
import cudra.mohamed.workout_log.R
import cudra.mohamed.workout_log.models.ExerciseCategory

class ExerciseCategoryAdapter (context: Context,var categories:List<ExerciseCategory>)
    :ArrayAdapter<ExerciseCategory>(context,0,categories){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getDropDownView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }
    fun getCustomView(position:Int, convertView: View?, parent: ViewGroup):View{
        val view =LayoutInflater.from(context).inflate(R.layout.custom_spinner_item,parent,false)
        val tvSpinnerText=view.findViewById<TextView>(R.id.tvSpinnerText)
        tvSpinnerText.text=categories.get(position).categoryName
        return view
    }
}