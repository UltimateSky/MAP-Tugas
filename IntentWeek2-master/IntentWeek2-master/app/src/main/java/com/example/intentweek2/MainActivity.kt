package com.example.intentweek2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.example.intentweek2.ResultActivity.Companion.EXTRA_COLOR_CODE
import com.example.intentweek2.ResultActivity.Companion.EXTRA_INPUT_ERROR

class MainActivity : AppCompatActivity() {

    private lateinit var colorCodeInput: TextInputEditText

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                val isError = data?.getBooleanExtra(EXTRA_INPUT_ERROR, false) ?: false
                if (isError) {
                    Toast.makeText(this, getString(R.string.error_invalid_color_code), Toast.LENGTH_LONG).show()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        colorCodeInput = findViewById(R.id.color_code_input_field)
        val submitButton = findViewById<Button>(R.id.submit_button)

        submitButton.setOnClickListener {
            val colorCode = colorCodeInput.text.toString().trim()
            if (colorCode.isEmpty()) {
                Toast.makeText(this, getString(R.string.error_color_code_empty), Toast.LENGTH_SHORT).show()
            } else {
                Intent(this, ResultActivity::class.java).also { intent ->
                    intent.putExtra(EXTRA_COLOR_CODE, colorCode)
                    resultLauncher.launch(intent)
                }
            }
        }
    }
}
