package com.fw.vamosrachar.activities

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.fw.vamosrachar.R
import com.fw.vamosrachar.data.DatabaseHelper

class SavedResultsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_results)

        val listView: ListView = findViewById(R.id.listView)
        val dbHelper = DatabaseHelper(this)
        val resultados = dbHelper.buscarDivisoes()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resultados)
        listView.adapter = adapter
    }

    fun voltarParaCalculadora(view: View) {
        finish()
    }
}
