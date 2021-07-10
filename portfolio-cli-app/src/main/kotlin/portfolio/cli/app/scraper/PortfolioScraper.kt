package portfolio.cli.app.scraper

import portfolio.cli.app.PortfolioRow
import portfolio.cli.app.credentials.LoginCredentials
import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.Point
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import portfolio.cli.app.ConsoleUtils
import java.util.concurrent.TimeUnit

abstract class PortfolioScraper
    () {
    lateinit var driver : ChromeDriver
    abstract val portfolioPageUrl: String
    val rows = mutableListOf<PortfolioRow>()
    abstract val name : String

    init {

    }

    fun start(credentials: LoginCredentials) {
        println()
        println("==============================")
        println("Starting Selenium Driver")
        println("==============================")
        println()

        val chromedriver = WebDriverManager.chromedriver()
        // TODO - how to make it work for everyone? argument or file with chrome version?
        chromedriver.config().chromeVersion = "91"
        chromedriver.setup()
        val options = ChromeOptions()
        options.addArguments("--window-size=800,600");

        driver = ChromeDriver(options)
        driver.manage().window().position = Point(-2000, 0)

        println()
        println("==============================")
        println("Selenium Driver Started")
        println("==============================")
        println()

//        ConsoleUtils.clearConsole()

        println("Loading $name")

        driver.get(portfolioPageUrl)

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)

        println("Logging In to $name")

        login(credentials)

        readEntirePortfolio()


    }

    fun close() = driver.close()

    fun readEntirePortfolio() {
        rows.clear()

        println("Reading Portfolio")

        scrapePortfolio()
    }

    // TODO - support 2FA for other websites?
    abstract fun login(credentials: LoginCredentials);

    abstract fun enterPortfolio();

    abstract fun scrapePortfolio()

}

