package com.example.echelleplan

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.echelleplan.databinding.ActivityMainBinding
import com.example.echelleplan.utils.viewBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding { ActivityMainBinding.inflate(it) }
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            val startingMeasurement = binding.startingMeasurementEditText.text.toString()
            val desiredMeasure = binding.desiredMeasureEditText.text.toString()

            if (validateInput(startingMeasurement, binding.startingMeasurementEditText) &&
                validateInput(desiredMeasure, binding.desiredMeasureEditText)
            ) {
                try {
                    val result = viewModel.calculateResult(
                        desiredMeasure.toDouble(),
                        startingMeasurement.toDouble()
                    )
                    binding.resultTextView.text = getString(R.string.result, result)
                } catch (e: NumberFormatException) {
                    binding.resultTextView.text = getString(R.string.error_invalid_number)
                }
            }

            closeKeyboard(this)
        }

    }

    private fun validateInput(input: String, editText: EditText): Boolean {
        return if (input.isEmpty()) {
            editText.error = getString(R.string.empty_value)
            false
        } else {
            true
        }
    }

    private fun closeKeyboard(activity: Activity) {
        val view = activity.currentFocus
        if (view != null) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}
