package com.fw.vamosrachar.model

public class MoneyDivision(private val totalMoney: Float, private val amountOfPeopleToDive: Int) {
    fun calcMoneyForEachPerson(): Float {
        return totalMoney / amountOfPeopleToDive
    }

    fun formatMoneyValueToString(money: Float): String {
        return Currency(CurrencyCode.BRL).format(money)
    }
}
