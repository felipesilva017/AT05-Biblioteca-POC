package com.fw.vamosrachar.activities

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.fw.vamosrachar.R

class ShareReceiverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share2)

        val innerText: TextView = findViewById(R.id.tvShare)
        innerText.text = intent.extras?.getString("mensagem") ?: "Num tem"

        val outerText: TextView = findViewById(R.id.tvShareExt)
        outerText.text = intent.extras?.getString(Intent.EXTRA_TEXT)
    }
}
