package portfolio.cli.app

import portfolio.cli.app.scraper.PortfolioScraper
import java.time.LocalDateTime

class PortfolioPrinter(private val scraper: PortfolioScraper) {
    fun print() {

        ConsoleUtils.clearConsole()

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

        println()

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
            // name
            .append(ConsoleColors.WHITE_BOLD)
            .append(name.padEnd(30))
            // average price
            .append(ConsoleColors.RESET)
            .append(averagePrice.padEnd(12))
            // amount
            .append(amount.padEnd(12))
            // last price
            .append(lastPrice.padEnd(12))
            // value
            .append(value.padEnd(12))
            // daily pl percentage
            .append(getColorForValue(dailyPlPercentage))
            .append(dailyPlPercentage.padEnd(12))
            // pl percentage
            .append(getColorForValue(plPercentage))
            .append(plPercentage.padEnd(12))
            // pl amount
            .append(getColorForValue(plAmount))
            .append(plAmount.padEnd(12))

        println(builder.toString())

        // reset console color
        print(ConsoleColors.RESET)

    }

    private fun getColorForValue(value: String): Any {
        val doubleValue = value
            .replace(",", "")
            .replace(",", "")
            .replace("%", "")
            .toDoubleOrNull()

        return when {
            doubleValue == null -> ConsoleColors.RESET
            doubleValue > 0 -> ConsoleColors.GREEN
            doubleValue < 0 -> ConsoleColors.RED
            else -> ConsoleColors.RESET
        }


    }


}
