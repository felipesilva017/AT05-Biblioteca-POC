package com.fw.vamosrachar

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ShareActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share2)

        val innerText: TextView = findViewById(R.id.tvShare)
        val paramTela = intent.extras?.getString("mensagem")
        innerText.text = if (paramTela == null) "Num tem" else paramTela

        val outerText: TextView = findViewById(R.id.tvShareExt)
        outerText.text = intent.extras?.getString(Intent.EXTRA_TEXT)
    }
}
