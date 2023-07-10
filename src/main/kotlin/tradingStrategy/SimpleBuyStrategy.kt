import tradingStrategy.config.TradingStrategyConfig
import java.util.*

class SimpleBuyStrategy(
    private val initialPortfolio: Portfolio,
    strategyConfig: TradingStrategyConfig): BaseTradingStrategy(strategyConfig) {

    override fun run() {

    }

    override fun value(date: Date): StockPeriodValue? {
        return config.dataProvider.value(initialPortfolio, date)
    }


}