package portfolio.cli.app.scraper

import portfolio.cli.app.scraper.impl.LeumiHebrewPortfolioScraper

val scraperIndex = hashMapOf<String, PortfolioScraper>("leumi-il" to LeumiHebrewPortfolioScraper())
