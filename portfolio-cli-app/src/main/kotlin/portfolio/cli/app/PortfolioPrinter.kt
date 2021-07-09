package portfolio.cli.app

import portfolio.cli.app.scraper.PortfolioScraper
import java.time.LocalDateTime

//const val ANSI_RED = "\u001B[31m"
//const val ANSI_GREEN = "\u001B[32m"

class PortfolioPrinter(private val scraper: PortfolioScraper) {
    fun print() {

        println("\n")
        println("Printing Portfolio")
        println("\n")

        val builder = StringBuilder()
//            .append("\u2066")
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
//            .append("\u2066")
            .append(name.padEnd(30))
            .append(averagePrice.padEnd(12))
            .append(amount.padEnd(12))
            .append(lastPrice.padEnd(12))
            .append(value.padEnd(12))
            .append(dailyPlPercentage.padEnd(12))
            .append(plPercentage.padEnd(12))
            .append(plAmount.padEnd(12))


        println(builder.toString())

        println("\n")

        println("Last Print: ${LocalDateTime.now()}")

        println("\n")

//            val plAmount = it.plAmount.toDouble()
//
//            if (plAmount > 0) {
//                println("$ANSI_GREEN $it")
//            } else if (plAmount < 0) {
//                println("$ANSI_RED $it")
//            }

    }


}
