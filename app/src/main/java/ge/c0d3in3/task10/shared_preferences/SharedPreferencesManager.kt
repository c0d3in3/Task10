package ge.c0d3in3.task10.shared_preferences

import android.content.Context
import com.google.gson.Gson
import ge.c0d3in3.task10.model.User

class SharedPreferencesManager(private val context: Context) {

    private val sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun saveUser(user: User) {
        val json = Gson().toJson(user)
        sharedPreferences.edit().putString(USER_KEY, json).apply()
    }

    fun getUser(): User? {
        val json = sharedPreferences.getString(USER_KEY, null)
        return Gson().fromJson(json, User::class.java)
    }

    companion object {
        private const val SHARED_PREFERENCES_NAME = "TASK-10-APP"
        private const val USER_KEY = "user"
    }
}