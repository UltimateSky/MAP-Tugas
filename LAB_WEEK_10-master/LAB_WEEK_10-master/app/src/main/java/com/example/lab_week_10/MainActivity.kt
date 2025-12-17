package com.example.lab_week_10

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.lab_week_10.database.TotalDatabase
import com.example.lab_week_10.database.Total
import com.example.lab_week_10.database.TotalObject
import com.example.lab_week_10.viewmodels.TotalViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date

class MainActivity : AppCompatActivity() {

    private val db by lazy { TotalDatabase.getDatabase(this) }
    private val viewModel by lazy {
        ViewModelProvider(this)[TotalViewModel::class.java]
    }


    companion object {
        const val ID: Long = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeValueFromDatabase()
        prepareViewModel()
    }

    private fun initializeValueFromDatabase() {

        lifecycleScope.launch(Dispatchers.IO) {
            val result = db.totalDao().getTotal(ID)

            if (result.isEmpty()) {
                db.totalDao().insert(
                    Total(
                        id = ID,
                        total = TotalObject(
                            value = 0,
                            date = Date().toString()
                        )
                    )
                )

                withContext(Dispatchers.Main) {
                    viewModel.setTotal(0)
                }
            } else {
                val obj = result.first().total

                withContext(Dispatchers.Main) {
                    viewModel.setTotal(obj.value)
                }
            }
        }
    }

    private fun prepareViewModel() {
        val textTotal = findViewById<TextView>(R.id.text_total)
        val btnIncrement = findViewById<Button>(R.id.button_increment)

        viewModel.total.observe(this) { value ->
            textTotal.text = getString(R.string.text_total, value)
        }

        btnIncrement.setOnClickListener {
            viewModel.incrementTotal()
        }
    }

    override fun onStop() {
        super.onStop()

        val value = viewModel.total.value ?: 0
        val date = Date().toString()


        lifecycleScope.launch(Dispatchers.IO) {
            db.totalDao().update(
                Total(
                    id = ID,
                    total = TotalObject(
                        value = value,
                        date = date
                    )
                )
            )
        }
    }

    override fun onStart() {
        super.onStart()


        lifecycleScope.launch(Dispatchers.IO) {
            val result = db.totalDao().getTotal(ID)
            if (result.isNotEmpty()) {
                val savedDate = result.first().total.date

                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Last Updated: $savedDate", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}
