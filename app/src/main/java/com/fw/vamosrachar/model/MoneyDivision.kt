package com.fw.vamosrachar.model

class MoneyDivision(private val totalMoney: Float, private val amountOfPeopleToDivide: Int) {
    init {
        require(totalMoney >= 0) { "totalMoney must not be less than zero." }
        require(amountOfPeopleToDivide > 0) { "amountOfPeopleToDivide must not be zero." }
    }

    fun calcMoneyForEachPerson(): Float {
        return totalMoney / amountOfPeopleToDivide
    }

    fun formatMoneyValueToString(money: Float): String {
        return Currency(CurrencyCode.BRL).format(money)
    }
}
