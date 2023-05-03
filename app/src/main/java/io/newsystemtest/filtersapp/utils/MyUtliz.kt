package io.newsystemtest.filtersapp.utils

import android.util.Log
import org.json.JSONObject

class MyUtliz {
    companion object{
        fun getErrorMessage(ErrorBody: String?): String? {
            return try {
                val jObjError = JSONObject(ErrorBody)
                Log.d("error", jObjError.getString("message"))
                jObjError.getString("message")
            } catch (e: java.lang.Exception) {
                e.localizedMessage
            }
        }
    }

}