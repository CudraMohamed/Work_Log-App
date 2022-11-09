package cudra.mohamed.workout_log.database

import androidx.room.TypeConverter

class Converters{
    @TypeConverter
    fun listToString( listX:List<String>):String {
        return listX.joinToString(",")
    }
    @TypeConverter
    fun stringToList(stringX:String):List<String>{
        return stringX.split(",")
    }
//    var outputString=""
//    listX.forEach
//    {
//        item->
//        outputString += "$item,"
//    }
//    return outputString
}