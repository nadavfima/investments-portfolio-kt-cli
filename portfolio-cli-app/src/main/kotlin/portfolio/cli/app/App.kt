/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package portfolio.cli.app


import portfolio.cli.app.credentials.LoginCredentialsLoader
import portfolio.cli.app.scraper.scraperIndex
import java.util.concurrent.TimeUnit
import java.util.logging.Level
import java.util.logging.Logger


object App {
    const val appName = "Investments Portfolio Kotlin CLI"
}


fun main(args: Array<String>) {
    Logger.getGlobal().level = Level.WARNING

    println()
    println(App.appName)

    val credentialsList = LoginCredentialsLoader.load();

    // TODO - handle multiple credentials in list
    credentialsList.firstOrNull()?.let { credentials ->
        val scraper = scraperIndex[credentials.scraper]

        // uncomment to test mock scraper
        // val scraper = scraperIndex["mock"]

        scraper!!.init()

        scraper.start(credentials)

        scraper.login(credentials)

        scraper.readEntirePortfolio()

        if (scraper.rows.isEmpty()) {
            print("Could not find portfolio rows")
            return
        }

        PortfolioPrinter(scraper).print()

        while (true) {
            Thread.sleep(TimeUnit.MINUTES.toMillis(1))

            scraper.enterPortfolio()

            Thread.sleep(10000)

            scraper.readEntirePortfolio()

            PortfolioPrinter(scraper).print()
        }
    }


}
