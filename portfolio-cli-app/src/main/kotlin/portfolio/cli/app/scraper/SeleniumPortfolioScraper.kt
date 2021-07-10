package portfolio.cli.app.scraper

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.Point
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import portfolio.cli.app.ConsoleColors
import portfolio.cli.app.credentials.LoginCredentials
import java.util.concurrent.TimeUnit

abstract class SeleniumPortfolioScraper : PortfolioScraper() {

    lateinit var driver: ChromeDriver
    abstract val portfolioPageUrl: String


    override fun init() {

        println()
        println(ConsoleColors.YELLOW +
                "Starting Selenium Driver")
        println(ConsoleColors.RESET)

        val chromedriver = WebDriverManager.chromedriver()
        // TODO - how to make it work for everyone? argument or file with chrome version?
        chromedriver.config().chromeVersion = "91"
        chromedriver.setup()
        val options = ChromeOptions()
        options.addArguments("--window-size=800,600");

        // this doesn't work for my attempts with Leumi
        // options.setHeadless(true)

        driver = ChromeDriver(options)
        driver.manage().window().position = Point(-2000, 0)

        Thread.sleep(1000)

        println()
        println(ConsoleColors.GREEN +
                "Selenium Driver Started")
        println(ConsoleColors.RESET)

    }

    override fun start(credentials: LoginCredentials) {
        super.start(credentials)

        // ConsoleUtils.clearConsole()

        driver.get(portfolioPageUrl)

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)

        println("Logging In to $name")



    }

    override fun close() {
        super.close()
        driver.close()
    }
}