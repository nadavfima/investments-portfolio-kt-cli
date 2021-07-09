package portfolio.cli.app.scraper

import portfolio.cli.app.PortfolioRow
import portfolio.cli.app.credentials.LoginCredentials
import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import java.util.concurrent.TimeUnit

abstract class PortfolioScraper
    () {
    protected val driver : ChromeDriver
    abstract val portfolioPageUrl: String
    val rows = mutableListOf<PortfolioRow>()
    abstract val name : String

    init {
        val chromedriver = WebDriverManager.chromedriver()
        // TODO - how to make it work for everyone? argument or file with chrome version?
        chromedriver.config().chromeVersion = "91"
        chromedriver.setup()
        val options = ChromeOptions()
//        options.addArguments("--headless", "--window-size=1920,1200")
        driver = ChromeDriver(options)
    }

    fun start(credentials: LoginCredentials) {

        driver.get(portfolioPageUrl)

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)

        println("Logging In to $name")

        login(credentials)

        readEntirePortfolio()

        driver.close()
    }
    fun readEntirePortfolio() {

        println("Reading Portfolio")
        println("\n")

        scrapePortfolio()
    }

    // TODO - support 2FA for other websites?
    abstract fun login(credentials: LoginCredentials);

    abstract fun enterPortfolio();

    abstract fun scrapePortfolio()

}

