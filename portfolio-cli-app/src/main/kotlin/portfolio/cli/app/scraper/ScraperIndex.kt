package portfolio.cli.app.scraper

import portfolio.cli.app.scraper.impl.LeumiEnglishPortfolioScraper
import portfolio.cli.app.scraper.impl.LeumiHebrewPortfolioScraper

val scraperIndex = hashMapOf(
    "mock" to MockPortfolioScraper(),
    "leumi-il" to LeumiHebrewPortfolioScraper(),
    "leumi-en" to LeumiEnglishPortfolioScraper()
)
