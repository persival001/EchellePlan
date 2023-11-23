package com.example.echelleplan

import androidx.lifecycle.ViewModel

class MainViewModel(
) : ViewModel() {

    fun calculateResult(desiredMeasure: Double, startingMeasurement: Double): String {
        val result = (desiredMeasure / startingMeasurement) * 100
        return "%.3f".format(result)
    }

}