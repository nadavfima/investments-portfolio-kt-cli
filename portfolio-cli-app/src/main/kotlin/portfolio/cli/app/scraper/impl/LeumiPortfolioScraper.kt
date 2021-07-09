package portfolio.cli.app.scraper.impl


class LeumiEnglishPortfolioScraper : BaseLeumiPortfolioScraper() {

    override val name: String
        get() = "Leumi EN"

    override val holdingsTitle: String
        get() = "Holdings"

    override val portfolioPageUrl: String
        get() = "https://hb2.bankleumi.co.il/lti/lti-eng/trade/portfolio"


}


/// TODO - doesn't work great when printing because of RTL issues
class LeumiHebrewPortfolioScraper : BaseLeumiPortfolioScraper() {

    override val name: String
        get() = "Leumi IL"

    override val holdingsTitle: String
        get() = "אחזקות"

    override val portfolioPageUrl: String
        get() = "https://hb2.bankleumi.co.il/lti/lti-app/trade/portfolio"


}