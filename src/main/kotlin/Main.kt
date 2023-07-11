import tradingStrategy.config.BasicTradingStrategyConfig

fun main(args: Array<String>) {
    val parser = FinancialDataParser(args[0])
    val data = parser.parse()
    data?.let {
        val dataProvider = StockDataProviderImpl(it)
        val config = BasicTradingStrategyConfig(dataProvider,
            TradingDate("2012-04-26"), TradingDate("2023-04-26"))
        val tickers = listOf(Pair(0.5f, "VOO"), Pair(0.5f, "SPXEW"))
        val portfolio = PortfolioBuilder.build(1000f, TradingDate("2012-04-26"),
            tickers, dataProvider)
        val holdStrategy = SimpleBuyStrategy(portfolio, config)
        val balancingStrategy = BalancingStrategy(portfolio, config, 210, tickers)
        holdStrategy.run()
        balancingStrategy.run()
        val startValue = holdStrategy.value(config.startDate)
        val holdValue = holdStrategy.value(config.endDate)
        val balancingValue = balancingStrategy.value(config.endDate)
        print(startValue)
        print(holdValue)
        print(balancingValue)
    }
}
