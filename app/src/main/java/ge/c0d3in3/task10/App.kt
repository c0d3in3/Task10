package ge.c0d3in3.task10

import android.app.Application
import ge.c0d3in3.task10.shared_preferences.SharedPreferencesManager

class App: Application() {

    companion object {
        lateinit var sharedPreferencesManager: SharedPreferencesManager
            private set
    }

    override fun onCreate() {
        super.onCreate()
        sharedPreferencesManager = SharedPreferencesManager(applicationContext)
    }
}