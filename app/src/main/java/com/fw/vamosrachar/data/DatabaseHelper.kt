package com.fw.vamosrachar.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "divisoes.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE divisao (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "valor_total REAL," +
                    "quantidade_pessoas INTEGER," +
                    "resultado_final TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS divisao")
        onCreate(db)
    }

    fun salvarDivisao(valorTotal: Float, qtdPessoas: Int, resultadoFinal: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("valor_total", valorTotal)
            put("quantidade_pessoas", qtdPessoas)
            put("resultado_final", resultadoFinal)
        }
        return db.insert("divisao", null, values)
    }

    fun buscarDivisoes(): List<String> {
        val lista = mutableListOf<String>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM divisao", null)
        if (cursor.moveToFirst()) {
            do {
                val valor = cursor.getFloat(cursor.getColumnIndexOrThrow("valor_total"))
                val pessoas = cursor.getInt(cursor.getColumnIndexOrThrow("quantidade_pessoas"))
                val resultado = cursor.getString(cursor.getColumnIndexOrThrow("resultado_final"))
                lista.add("R$ $valor ÷ $pessoas = $resultado")
            } while (cursor.moveToNext())
        }
        cursor.close()
        return lista
    }
}
