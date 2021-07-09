package portfolio.cli.app.scraper

import portfolio.cli.app.scraper.impl.LeumiEnglishPortfolioScraper
import portfolio.cli.app.scraper.impl.LeumiHebrewPortfolioScraper

val scraperIndex = hashMapOf<String, PortfolioScraper>(
    "leumi-il" to LeumiHebrewPortfolioScraper(),
    "leumi-en" to LeumiEnglishPortfolioScraper()
)
