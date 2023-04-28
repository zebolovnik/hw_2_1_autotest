package ru.netology
fun main() {
    println("Сумма комиссии составит: ${commissions(100_000, TYPE_MASTERCARD, 0)} руб.")
    println("Сумма комиссии составит: ${commissions(76000)} руб.")
    println("Сумма комиссии составит: ${commissions(500_000, TYPE_MAESTRO)} руб.")
    println("Сумма комиссии составит: ${commissions(100_000, TYPE_MAESTRO, -1000_000)} руб.")
    println("Сумма комиссии составит: ${commissions(500_000, "Сбер", 0)} руб.")
}

const val LIMIT_CARD = 75_000 // лимит в календарный мес, после которого начисл комиссия
const val PERCENT = 0.6 // процентКомиссии 0,6%
const val TYPE_MASTERCARD = "MasterCard"
const val TYPE_MAESTRO = "Maestro"
const val TYPE_VK = "VK Pay"
const val ERROR_CARD = -1
const val ERROR_LIMIT = -2

fun commissions(
    transferAmount: Int, // сумма совершаемого перевода
    typeCard: String = TYPE_VK, // тип карты/счёта
    amountPerMonth: Int = 0 // сумма всех переводов в этом месяце
): Int {
    val sum = amountPerMonth + transferAmount
    val calcCommission = transferAmount * (commissionByCardType(typeCard) / 100) // расчет суммы комиссии
    return when (typeCard) {
        TYPE_MASTERCARD, TYPE_MAESTRO, TYPE_VK -> when {
            sum > LIMIT_CARD && typeCard != TYPE_VK -> (calcCommission + 20).toInt()
            sum >= LIMIT_CARD && typeCard == TYPE_VK -> 0
            sum == LIMIT_CARD -> 0
            else -> ERROR_LIMIT
        }
        else -> {
            println("Ввели неверный тип карты: $typeCard")
            ERROR_CARD
        }
    }
}

fun commissionByCardType(typeCard: String) = when (typeCard) {
    TYPE_VK -> 0.0
    TYPE_MASTERCARD, TYPE_MAESTRO -> PERCENT
    else -> ERROR_CARD.toDouble()
//    else -> throw IllegalArgumentException("Неверный тип карты: $typeCard")
}