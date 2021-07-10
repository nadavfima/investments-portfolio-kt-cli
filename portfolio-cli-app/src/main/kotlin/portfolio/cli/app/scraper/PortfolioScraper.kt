package portfolio.cli.app.scraper

import portfolio.cli.app.PortfolioRow
import portfolio.cli.app.credentials.LoginCredentials
import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.Point
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import portfolio.cli.app.ConsoleUtils
import java.util.concurrent.TimeUnit

abstract class PortfolioScraper() {

    val rows = mutableListOf<PortfolioRow>()
    abstract val name: String

    init {

    }

    open fun init(){

    }

    fun onInitDone(){
        println("Loading $name")
    }

    open fun start(credentials: LoginCredentials) {

        onInitDone()



    }

    open fun close(){
        rows.clear()
    }

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

