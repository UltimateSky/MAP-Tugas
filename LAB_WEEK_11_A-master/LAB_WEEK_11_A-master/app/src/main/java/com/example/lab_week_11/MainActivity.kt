package com.example.lab_week_11

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get settings store from the application
        val settingsStore = (application as SettingsApplication).settingsStore

        // Create the ViewModel using a factory
        val settingsViewModel = ViewModelProvider(
            this,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return SettingsViewModel(settingsStore) as T
                }
            }
        )[SettingsViewModel::class.java]

        // Observe the text LiveData
        settingsViewModel.textLiveData.observe(this) { value ->
            findViewById<TextView>(R.id.activity_main_text_view).text = value
        }

        // Save text when button is clicked
        findViewById<Button>(R.id.activity_main_button).setOnClickListener {
            val text = findViewById<EditText>(R.id.activity_main_edit_text)
                .text.toString()
            settingsViewModel.saveText(text)
        }
    }
}