package com.fw.vamosrachar.model

class MoneyDivision(private val totalMoney: Float, private val amountOfPeopleToDivide: Int) {
    fun calcMoneyForEachPerson(): Float {
        return totalMoney / amountOfPeopleToDivide
    }

    fun formatMoneyValueToString(money: Float): String {
        return Currency(CurrencyCode.BRL).format(money)
    }
}
