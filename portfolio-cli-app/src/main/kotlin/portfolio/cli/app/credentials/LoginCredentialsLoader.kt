package portfolio.cli.app.credentials

import org.apache.commons.io.FileUtils
import java.io.File
import java.nio.charset.Charset

object LoginCredentialsLoader {
    fun load(): List<LoginCredentials> {

        val file = File("credentials")

        println("Looking for credentials in: ${file.absolutePath}")

        val credentialsRaw = FileUtils.readFileToString(file, Charset.defaultCharset())

        val listOfPortfolioCredentialsRaw = credentialsRaw.split("\n\n")

        return listOfPortfolioCredentialsRaw.map { s ->
            val data = s.split("\n")
            return@map LoginCredentials(
                data[0],
                data[1],
                data[2]
            )
        }
    }

}
