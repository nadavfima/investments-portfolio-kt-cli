package portfolio.cli.app.scraper.impl

import portfolio.cli.app.PortfolioRow
import portfolio.cli.app.credentials.LoginCredentials
import portfolio.cli.app.scraper.PortfolioScraper
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import java.lang.Exception
import java.util.concurrent.TimeUnit
import java.util.function.Supplier
import java.util.logging.Level
import java.util.logging.Logger

abstract class BaseLeumiPortfolioScraper : PortfolioScraper() {


    override fun login(credentials: LoginCredentials) {

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS)

        val usernameField = driver.findElementById("uid");
        val passwordField = driver.findElementById("password");

        usernameField.sendKeys(credentials.username)
        passwordField.sendKeys(credentials.password)

        driver.findElementById("enter").click()

        // let login finish before we lookup the next element
        Thread.sleep(10000)

    }

    override fun enterPortfolio() {

        // should already be in portfolio

        driver.get(portfolioPageUrl)

        Thread.sleep(10000)


    }

    override fun scrapePortfolio() {
        // TODO - support multiple pages portfolio

        // find portfolio table element
        val tableBody = driver.findElementByCssSelector("div[class='tbl-body']")

        // find the children rows of the table
        val webRows = tableBody.findElements(By.xpath("*"))

        // map each row to data
        rows.addAll(mapPortfolioToData(webRows).filterNotNull())

    }


    private fun mapPortfolioToData(webRows: MutableList<WebElement>): List<PortfolioRow?> = webRows.map { rowElement ->

        val columns = rowElement.findElements(By.tagName("div"))

        val title = getNameElement(columns)

        title?.let {
            title.text?.let {
                println("Loading Position: ${title.text}")
            }
        }


        try {

            val averageRate = getAverageRateElement(columns)
            val amount = getAmountElement(columns)
            val lastRate = getLastRateElement(columns)
            val value = getValueElement(columns)
            val changePercent = getChangePercentElement(columns)
            val profitPercent = getProfitPercentElement(columns)
            val profitAmount = getProfitAmountElement(columns)

            return@map PortfolioRow(
                title?.text,
                averageRate?.text,
                amount?.text,
                lastRate?.text,
                value?.text,
                changePercent?.text,
                profitPercent?.text,
                profitAmount?.text
            )

        } catch (e: Exception) {
            println("Could not find all data for ${title?.text}")
            Logger.getGlobal().log(Level.WARNING, e, Supplier { return@Supplier e.message })
            return@map null
        }
    }

    private fun getNameElement(column: MutableList<WebElement>) =
        column.first { it.getAttribute("class").contains("paperName") }
            .findElement(By.tagName("span"))

    private fun getValueElement(column: MutableList<WebElement>) =
        column.first { it.getAttribute("class") == "tbl-cell Value number ng-star-inserted" }
            .findElement(By.tagName("span"))

    private fun getLastRateElement(column: MutableList<WebElement>) =
        column.first { it.getAttribute("class") == "tbl-cell PaperLastRateForStatement number ng-star-inserted" }
            .findElement(By.tagName("span"))

    private fun getAmountElement(column: MutableList<WebElement>) =
        column.first { it.getAttribute("class") == "tbl-cell Amount number ng-star-inserted" }
            .findElement(By.tagName("span"))

    private fun getAverageRateElement(column: MutableList<WebElement>) =
        column.first { it.getAttribute("class") == "tbl-cell AverageRate number ng-star-inserted" }
            .findElement(By.tagName("span"))

    private fun getProfitAmountElement(column: MutableList<WebElement>) =
        column.first { it.getAttribute("class").startsWith("tbl-cell ProfitCash") }
            .findElement(By.tagName("span"))

    private fun getProfitPercentElement(column: MutableList<WebElement>) =
        column.first { it.getAttribute("class").startsWith("tbl-cell ProfitPercent") }
            .findElement(By.tagName("span"))

    private fun getChangePercentElement(column: MutableList<WebElement>) =
        column.first { it.getAttribute("class").startsWith("tbl-cell ChangePercent") }
            .findElement(By.tagName("span"))


}

