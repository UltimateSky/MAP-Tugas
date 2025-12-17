package com.example.lab_week_11

import android.app.Application
import android.content.Context

class PreferenceApplication : Application() {

    lateinit var preferenceWrapper: PreferenceWrapper

    override fun onCreate() {
        super.onCreate()

        // Initialize the preference wrapper
        // The preference wrapper is used to access the shared preferences
        preferenceWrapper = PreferenceWrapper(
            // Get the shared preferences
            // Shared preferences stored in:
            // /data/data/com.example.lab_week_11_a/shared_prefs/prefs.xml
            getSharedPreferences(
                "prefs",              // The name of the file
                Context.MODE_PRIVATE  // Only this app can access it
            )
        )
    }
}