package com.example.calculatormorebuttons

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.calculatormorebuttons.databinding.ActivityMainBinding

// TODO: Fix a few bugs
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var firstNumberStr: String? = null
    private var selectedOperation: String? = null
    private var multipleNumber: String? = null
    private var ifTypeCheck: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
        clearAll()
        binding.buttonClear.setOnClickListener {
            clearAll()
        }
        binding.button0.setOnClickListener {
            numberChange("0")
        }
        binding.button1.setOnClickListener {
            numberChange("1")
        }
        binding.button2.setOnClickListener {
            numberChange("2")
        }
        binding.button3.setOnClickListener {
            numberChange("3")
        }
        binding.button4.setOnClickListener {
            numberChange("4")
        }
        binding.button5.setOnClickListener {
            numberChange("5")
        }
        binding.button6.setOnClickListener {
            numberChange("6")
        }
        binding.button7.setOnClickListener {
            numberChange("7")
        }
        binding.button8.setOnClickListener {
            numberChange("8")
        }
        binding.button9.setOnClickListener {
            numberChange("9")
        }
        binding.buttonDecimal.setOnClickListener {
            numberChange(".")
        }
        binding.buttonPlus.setOnClickListener {
            operationFunction("plus")
        }
        binding.buttonMinus.setOnClickListener {
            operationFunction("minus")
        }
        binding.buttonMultiply.setOnClickListener {
            operationFunction("multiply")
        }
        binding.buttonDivide.setOnClickListener {
            operationFunction("divide")
        }
        binding.buttonCalculate.setOnClickListener {
            calculate()
        }
    }
    private fun clearAll() {
        binding.textviewCalculations.text = "0"
        firstNumberStr = null
        selectedOperation = null
        multipleNumber = null
        ifTypeCheck = false
    }
    private fun numberChange(pressedButton: String) {
        if (ifTypeCheck) {
            ifTypeCheck = false
        }
        var shownNumber = binding.textviewCalculations.text.toString().trim()
        shownNumber = when (firstNumberStr) {
            null -> shownNumber
            else -> ""
        }
        when (shownNumber) {
            "0" -> {
                shownNumber = when (pressedButton) {
                    "0" -> shownNumber
                    else -> ""
                }
            }
        }
        when (pressedButton) {
            "0" -> {
                when (shownNumber) {
                    "0" -> return
                    else -> shownNumber += "0"
                }
            }
            "1" -> shownNumber += "1"
            "2" -> shownNumber += "2"
            "3" -> shownNumber += "3"
            "4" -> shownNumber += "4"
            "5" -> shownNumber += "5"
            "6" -> shownNumber += "6"
            "7" -> shownNumber += "7"
            "8" -> shownNumber += "8"
            "9" -> shownNumber += "9"
            "." -> {
                when (shownNumber.contains(".")) {
                    true -> return
                    false -> {
                        when (shownNumber) {
                            "" -> shownNumber = "0."
                            else -> shownNumber += "."
                        }
                    }
                }
            }
        }
        binding.textviewCalculations.text = shownNumber.trim()
    }

    private fun operationFunction(operation: String) {
        selectedOperation = operation
        when (firstNumberStr) {
            null -> {
                firstNumberStr = binding.textviewCalculations.text.toString().trim()
                ifTypeCheck = true
            }
            else -> {
                when (ifTypeCheck) {
                    true -> return
                    false -> {
                        calculateAuto()
                        ifTypeCheck = true
                    }
                }
            }
        }
    }
    private fun calculateAuto() {
        val firstNumber = firstNumberStr.toString().trim().toDouble()
        val secondNumberStr = binding.textviewCalculations.text.toString().trim()
        val secondNumber = secondNumberStr.trim().toDouble()
        val result: Double
        when (selectedOperation) {
            "plus" -> {
                result = firstNumber + secondNumber
            }
            "minus" -> {
                result = firstNumber - secondNumber
            }
            "multiply" -> {
                result = firstNumber * secondNumber
            }
            "divide" -> {
                if (secondNumber == 0.0) {
                    binding.textviewCalculations.text = "division_By_Zero_Error"
                    return
                } else {
                    result = firstNumber / secondNumber
                }
            }
            else -> {
                binding.textviewCalculations.text = "invalid_Operation_Error"
                return
            }
        }
        val formattedResult = if (result % 1.0 == 0.0) {
            String.format("%d", result.toInt())
        } else {
            String.format("%.1f", result)
        }
        val resultStr = formattedResult.trim()
        binding.textviewCalculations.text = resultStr
        firstNumberStr = resultStr
        selectedOperation = null
    }
    private fun calculate() {
        if (firstNumberStr.isNullOrBlank()) {
            return
        }
        val firstNumber = firstNumberStr.toString().trim().toDouble()
        val secondNumberStr = if (multipleNumber.isNullOrBlank()) {
            binding.textviewCalculations.text.toString().trim()
        } else {
            multipleNumber.toString().trim()
        }
        val secondNumber = secondNumberStr.toDouble()
        val result: Double
        when (selectedOperation) {
            "plus" -> {
                result = firstNumber + secondNumber
            }
            "minus" -> {
                result = firstNumber - secondNumber
            }
            "multiply" -> {
                result = firstNumber * secondNumber
            }
            "divide" -> {
                if (secondNumber == 0.0) {
                    binding.textviewCalculations.text = "division_By_Zero_Error"
                    return
                } else {
                    result = firstNumber / secondNumber
                }
            }
            else -> {
                binding.textviewCalculations.text = "invalid_Operation_Error"
                return
            }
        }
        val formattedResult = if (result % 1.0 == 0.0) {
            String.format("%d", result.toInt())
        } else {
            String.format("%.1f", result)
        }
        val resultStr = formattedResult.trim()
        binding.textviewCalculations.text = resultStr
        multipleNumber = secondNumberStr
        firstNumberStr = binding.textviewCalculations.text.toString().trim()
        ifTypeCheck = false
    }
}