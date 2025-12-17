package com.example.intentweek2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.toColorInt

class ResultActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_COLOR_CODE = "com.example.intentweek2.EXTRA_COLOR_CODE"
        const val EXTRA_INPUT_ERROR = "com.example.intentweek2.EXTRA_INPUT_ERROR"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val colorCode = intent.getStringExtra(EXTRA_COLOR_CODE)
        val backgroundScreen = findViewById<ConstraintLayout>(R.id.background_screen)
        val resultMessage = findViewById<TextView>(R.id.color_code_result_message)
        val goBackButton = findViewById<Button>(R.id.button_go_back)

        if (colorCode != null) {
            try {
                val fullColorCode = if (colorCode.startsWith("#")) colorCode else "#$colorCode"
                backgroundScreen.setBackgroundColor(fullColorCode.toColorInt())
                resultMessage.text = getString(
                    R.string.color_code_result_message,
                    colorCode.uppercase()
                )
            } catch (ex: IllegalArgumentException) {
                Intent().let { errorIntent ->
                    errorIntent.putExtra(EXTRA_INPUT_ERROR, true)
                    setResult(Activity.RESULT_OK, errorIntent)
                    finish()
                }
                return
            }
        } else {
            resultMessage.text = "Error: No color code provided."
            Intent().let { errorIntent ->
                errorIntent.putExtra(EXTRA_INPUT_ERROR, true)
                setResult(Activity.RESULT_OK, errorIntent)
            }
        }

        goBackButton.setOnClickListener {
            finish()
        }
    }
}
