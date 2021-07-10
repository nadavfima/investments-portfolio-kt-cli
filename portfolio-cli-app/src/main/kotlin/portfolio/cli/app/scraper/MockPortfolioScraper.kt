package portfolio.cli.app.scraper

import portfolio.cli.app.PortfolioRow
import portfolio.cli.app.credentials.LoginCredentials

class MockPortfolioScraper : PortfolioScraper() {

    override val name: String
        get() = "Example Portfolio"

    override fun login(credentials: LoginCredentials) = Thread.sleep(1000)

    override fun enterPortfolio() = Thread.sleep(1000)

    override fun scrapePortfolio() {
        rows.clear()

        rows.addAll(listOf(
            PortfolioRow(
                name = "Vanguard S&P 500",
                averagePrice = "365",
                amount = "20",
                lastPrice = "398",
                dailyPlPercentage = "0.85%",
                value = "7960",
                plAmount = "660",
                plPercentage = "9%"
            ),
            PortfolioRow(
                name = "Invesco Nasdaq Trust",
                averagePrice = "365",
                amount = "20",
                lastPrice = "332",
                dailyPlPercentage = "-0.85%",
                value = "7960",
                plAmount = "-660",
                plPercentage = "-9%"
            )
        ))
    }
}