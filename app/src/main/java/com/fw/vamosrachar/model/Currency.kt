package com.fw.vamosrachar.model

import java.text.DecimalFormat

class Currency(private val code: CurrencyCode) {
    fun format(money: Float): String {
        val currencyCodeStr = getCurrencyCodeSymbol()
        val moneyFormatted = DecimalFormat("#0.00").format(money)
        return "$currencyCodeStr $moneyFormatted"
    }

    private fun getCurrencyCodeSymbol(): String {
        return when (code) {
            CurrencyCode.BRL -> "R$"
            else -> throw IllegalArgumentException("Unsupported currency code: $code")
        }
    }
}

enum class CurrencyCode {
    BRL
}
