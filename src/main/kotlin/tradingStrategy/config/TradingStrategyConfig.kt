package tradingStrategy.config

import StockDataProvider
import TradingDate

interface TradingStrategyConfig {
    val dataProvider: StockDataProvider
    val startDate: TradingDate
    val endDate: TradingDate
}

class BasicTradingStrategyConfig(
    override val dataProvider: StockDataProvider,
    override val startDate: TradingDate,
    override val endDate: TradingDate): TradingStrategyConfig {

}