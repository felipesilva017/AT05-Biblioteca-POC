package com.fw.vamosrachar

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity(), TextWatcher, TextToSpeech.OnInitListener {
    private lateinit var tts: TextToSpeech
    private lateinit var edtConta: EditText
    private var ttsSucess: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtConta = findViewById<EditText>(R.id.edtConta)
        // edtConta.addTextChangedListener(this)
        // Initialize TTS engine
        tts = TextToSpeech(this, this)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        Log.d("PDM24", "Antes de mudar")
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        Log.d("PDM24", "Mudando")
    }

    override fun afterTextChanged(s: Editable?) {
        Log.d("PDM24", "Depois de mudar")

        val valor: Double

        if (s.toString().length > 0) {
            valor = s.toString().toDouble()
            Log.d("PDM24", "v: " + valor)
            //    edtConta.setText("9")
        }
    }

    fun clickFalar(v: View) {
        //        val intent = Intent(Intent.ACTION_VIEW).apply {
        //            data = Uri.parse("geo:-3.7478921,-38.5773834")
        //        }
        //        if (intent.resolveActivity(packageManager) != null) {
        //            startActivity(intent)
        //        }

        //        val intent = Intent(Intent.ACTION_WEB_SEARCH).apply {
        //            putExtra(SearchManager.QUERY, "Betoneiras")
        //        }
        //        if (intent.resolveActivity(packageManager) != null) {
        //            startActivity(intent)
        //        }
        if (tts.isSpeaking) {
            tts.stop()
        }
        if (ttsSucess) {
            Log.d("PDM23", tts.language.toString())
            tts.speak("O rafael é cheiroso", TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

    fun shareText(v: View) {
        //        val sendIntent: Intent = Intent().apply {
        //            action = Intent.ACTION_SEND
        //            putExtra(Intent.EXTRA_TEXT, edtConta.text.toString())
        //            type = "text/plain"
        //        }
        //        val shareIntent = Intent.createChooser(sendIntent, null)
        //        startActivity(shareIntent)

    }

    override fun onDestroy() {
        // Release TTS engine resources
        tts.stop()
        tts.shutdown()
        super.onDestroy()
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // TTS engine is initialized successfully
            tts.language = Locale.getDefault()
            ttsSucess = true
            Log.d("PDM23", "Sucesso na Inicialização")
        } else {
            // TTS engine failed to initialize
            Log.e("PDM23", "Failed to initialize TTS engine.")
            ttsSucess = false
        }
    }
}
