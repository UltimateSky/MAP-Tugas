package com.example.week1

import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nameDisplay = findViewById<TextView>(R.id.name_display)
        val nameInput = findViewById<TextInputEditText>(R.id.name_input)
        val nameWrapper = findViewById<TextInputLayout>(R.id.name_wrapper)
        val studentNumberInput = findViewById<TextInputEditText>(R.id.student_number_input)
        val studentNumberWrapper = findViewById<TextInputLayout>(R.id.student_number_wrapper)
        val submitButton = findViewById<Button>(R.id.name_submit)

        submitButton.setOnClickListener {
            val name = nameInput.text.toString()
            val studentNumber = studentNumberInput.text.toString()

            var isValid = true

            if (studentNumber.length != 11) {
                studentNumberWrapper.error = getString(R.string.student_number_length_error)
                isValid = false
            } else {
                studentNumberWrapper.error = null
            }

            if (name.isNotEmpty()) {
                nameWrapper.error = null
            } else {
                nameDisplay.text = "Please enter your name."
                nameWrapper.error = "Name cannot be empty"
                isValid = false
            }

            if (isValid) {
                nameDisplay.text = "Hello, $name!\nStudent Number: $studentNumber"
                val toast = Toast.makeText(this, "Details Submitted!", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER, 0,0)
                toast.show()
            }
        }
    }
}
