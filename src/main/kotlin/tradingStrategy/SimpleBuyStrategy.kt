import tradingStrategy.config.TradingStrategyConfig

class SimpleBuyStrategy(
    private val initialPortfolio: Portfolio,
    strategyConfig: TradingStrategyConfig): BaseTradingStrategy(strategyConfig) {

    override fun run() {

    }

    override fun value(date: TradingDate): StockPeriodValue? {
        return config.dataProvider.value(initialPortfolio, date)
    }

}

