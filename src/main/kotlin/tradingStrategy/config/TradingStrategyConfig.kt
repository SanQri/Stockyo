package tradingStrategy.config

import StockDataProvider
import java.util.*

interface TradingStrategyConfig {
    val dataProvider: StockDataProvider
    val startDate: Date
    val endDate: Date
}

class BasicTradingStrategyConfig(
    override val dataProvider: StockDataProvider,
    override val startDate: Date,
    override val endDate: Date): TradingStrategyConfig {

}