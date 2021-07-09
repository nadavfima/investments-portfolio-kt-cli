package portfolio.cli.app

import portfolio.cli.app.scraper.PortfolioScraper
import java.time.LocalDateTime

const val ANSI_RESET = "\u001B[0m"
const val ANSI_RED = "\u001B[31m"
const val ANSI_GREEN = "\u001B[32m"

class PortfolioPrinter(private val scraper: PortfolioScraper) {
    fun print() {

        println()
        println("Portfolio Ready")
        println()

        val builder = StringBuilder()
            .append("Name".padEnd(30))
            .append("Avg Cost".padEnd(12))
            .append("Amount".padEnd(12))
            .append("Last Price".padEnd(12))
            .append("Value".padEnd(12))
            .append("Daily %".padEnd(12))
            .append("P/L %".padEnd(12))
            .append("P/L".padEnd(12))


        println(builder.toString())


        scraper.rows.forEach { printRow(it) }

        println()

        println("Last Print: ${LocalDateTime.now()}")

        println()

    }

    private fun printRow(it: PortfolioRow) {

        val name = it.name!!
        val averagePrice = it.averagePrice ?: ""
        val amount= it.amount?: ""
        val lastPrice = it.lastPrice?: ""

        val value = it.value?: ""
        val dailyPlPercentage = it.dailyPlPercentage?: ""
        val plPercentage = it.plPercentage?: ""
        val plAmount = it.plAmount?: ""

        val builder = StringBuilder()
            .append(name.padEnd(30))
            .append(averagePrice.padEnd(12))
            .append(amount.padEnd(12))
            .append(lastPrice.padEnd(12))
            .append(value.padEnd(12))
            .append(dailyPlPercentage.padEnd(12))
            .append(plPercentage.padEnd(12))
            .append(plAmount.padEnd(12))

        val plAmountD = plAmount.replace(",", "").toDouble()

        if (plAmountD > 0) {
            builder.insert(0, ANSI_GREEN)
        } else if (plAmountD < 0) {
            builder.insert(0, ANSI_RED)
        }

        println(builder.toString())


        // reset console color
        print(ANSI_RESET)

    }


}
