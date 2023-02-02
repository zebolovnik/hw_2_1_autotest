package ru.netology

import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun commissionsMasterCard() {
        val type = TYPE_MASTERCARD
        val transferAmount = 100_000
        val amountPerMonth = 0

        val result = commissions(
            typeCard = type,
            transferAmount = transferAmount,
            amountPerMonth = amountPerMonth
        )

        assertEquals(0, result)
    }
    @Test
    fun notCommissionsMasterCard() {
        val type = TYPE_MASTERCARD
        val transferAmount = 75_000
        val amountPerMonth = 0

        val result = commissions(
            typeCard = type,
            transferAmount = transferAmount,
            amountPerMonth = amountPerMonth
        )

        assertEquals(0, result)
    }

    @Test
    fun commissionsMaestro() {
        val type = TYPE_MAESTRO
        val transferAmount = 500_000
        val amountPerMonth = 0

        val result = commissions(
            typeCard = type,
            transferAmount = transferAmount,
            amountPerMonth = amountPerMonth
        )

        assertEquals(3020, result)
    }
    @Test
    fun notCommissionsMaestro() {
        val type = TYPE_MAESTRO
        val transferAmount = 75_000
        val amountPerMonth = 0

        val result = commissions(
            typeCard = type,
            transferAmount = transferAmount,
            amountPerMonth = amountPerMonth
        )

        assertEquals(0, result)
    }

    @Test
    fun commissionsVk() {
        val type = TYPE_VK
        val transferAmount = 100_000
        val amountPerMonth = 1000_000

        val result = commissions(
            typeCard = type,
            transferAmount = transferAmount,
            amountPerMonth = amountPerMonth
        )

        assertEquals(0, result)
    }
    @Test
    fun testWrongLimit() {
        val transferAmount = 0
        val amountPerMonth = -1

        val result = commissions(
            transferAmount = transferAmount,
            amountPerMonth = amountPerMonth
        )

        assertEquals(ERROR_LIMIT, result)
    }

    @Test
    fun testWrongCard() {
        val type = "MIR"
        val transferAmount = 75_000
        val amountPerMonth = 0

        val result = commissions(
            typeCard = type,
            transferAmount = transferAmount,
            amountPerMonth = amountPerMonth
        )

        assertEquals(ERROR_CARD, result)
    }
}