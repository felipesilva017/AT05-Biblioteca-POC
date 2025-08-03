package com.fw.vamosrachar.activities

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.fw.vamosrachar.R
import com.fw.vamosrachar.data.DatabaseHelper
import com.fw.vamosrachar.model.MoneyDivision
import java.util.*

class CalculatorActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private val tag = "CalculatorActivity"
    private lateinit var tts: TextToSpeech
    private var ttsSuccess: Boolean = false
    private lateinit var totalMoneyValue: EditText
    private lateinit var peopleQuantity: EditText
    private lateinit var moneyCalcResult: TextView
    private var totalMoney = 0.0f
    private var amountOfPeopleToDivide = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        totalMoneyValue = findViewById(R.id.totalMoneyValue)
        peopleQuantity = findViewById(R.id.peopleQuantity)
        moneyCalcResult = findViewById(R.id.moneyCalcResult)
        totalMoneyValue.addTextChangedListener(totalMoneyTextWatcher)
        peopleQuantity.addTextChangedListener(peopleQuantityTextWatcher)
        // Initialize TTS engine
        tts = TextToSpeech(this, this)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts.language = Locale.getDefault()
            ttsSuccess = true
            Log.d(tag, "Sucesso na Inicialização")
        } else {
            Log.e(tag, "Failed to initialize TTS engine.")
            ttsSuccess = false
        }
    }

    override fun onDestroy() {
        tts.stop()
        tts.shutdown()
        super.onDestroy()
    }

    private val totalMoneyTextWatcher: TextWatcher =
        object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                val textValue = s.toString()
                if (textValue.isNotEmpty()) {
                    val sanitizedString = MoneyDivision.formatMoneyToDecimal(textValue)
                    val money = sanitizedString.toFloat()
                    calculateMoneyForEachAndShowResult(money, null)
                    totalMoney = money
                } else {
                    moneyCalcResult.text = getString(R.string.initial_value_result)
                    totalMoney = 0.0f
                }
            }
        }

    private val peopleQuantityTextWatcher: TextWatcher =
        object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                val textValue = s.toString()
                if (textValue.isNotEmpty()) {
                    val sanitizedString = MoneyDivision.formatMoneyToDecimal(textValue)
                    val amountOfPeopleToDivideInt = sanitizedString.toInt()
                    calculateMoneyForEachAndShowResult(null, amountOfPeopleToDivideInt)
                    amountOfPeopleToDivide = amountOfPeopleToDivideInt
                } else {
                    moneyCalcResult.text = getString(R.string.initial_value_result)
                    amountOfPeopleToDivide = 1
                }
            }

        }

    private fun calculateMoneyForEachAndShowResult(
        money: Float?,
        amountOfPeopleToDivideParam: Int?,
    ) {
        val moneyValue = money ?: totalMoney
        val amountOfPeopleToDivideValue = amountOfPeopleToDivideParam ?: amountOfPeopleToDivide
        val moneyDivision = MoneyDivision(moneyValue, amountOfPeopleToDivideValue)
        val moneyForEachOne = moneyDivision.calcMoneyForEachPerson()
        val moneyForEachOneStr = moneyDivision.formatMoneyValueToString(moneyForEachOne)
        moneyCalcResult.text = moneyForEachOneStr
    }

    fun speakText(v: View) {
        if (tts.isSpeaking) {
            tts.stop()
        }
        if (ttsSuccess) {
            Log.d(tag, tts.voices.toString())
            tts.speak(moneyCalcResult.text.toString(), TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

    fun shareText(v: View) {
        val sendIntent: Intent =
            Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, moneyCalcResult.text.toString())
                type = "text/plain"
            }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    fun salvarResultado(v: View) {
        val resultadoFinal = moneyCalcResult.text.toString()
        val dbHelper = DatabaseHelper(this)
        dbHelper.salvarDivisao(totalMoney, amountOfPeopleToDivide, resultadoFinal)
    }

    fun verResultadosSalvos(v: View) {
        val intent = Intent(this, SavedResultsActivity::class.java)
        startActivity(intent)
    }
}
