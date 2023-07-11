import tradingStrategy.config.TradingStrategyConfig
import java.util.*

class BalancingStrategy(
    private val initialPortfolio: Portfolio,
    strategyConfig: TradingStrategyConfig,
    private val balancePeriodInDays: Int,
    private val fractions: List<Pair<Float, String>>
): BaseTradingStrategy(strategyConfig) {

    var currentPortfolio: Portfolio = initialPortfolio

    override fun run() {
        val calendar = Calendar.getInstance()
        calendar.time = config.startDate.date
        currentPortfolio = initialPortfolio
        while (calendar.time < config.endDate.date) {
            calendar.add(Calendar.DATE, balancePeriodInDays)
            currentPortfolio = rebalance(currentPortfolio, TradingDate(calendar.time))
        }
    }

    private fun rebalance(portfolio: Portfolio, date: TradingDate): Portfolio {
        val value = config.dataProvider.averagePrice(portfolio, date) ?: run {
            println("balancing failed")
            return portfolio
        }
        println("balancing successful")
        return PortfolioBuilder.build(value, date, fractions, config.dataProvider)
    }

    override fun value(date: TradingDate): StockPeriodValue? {
        return config.dataProvider.value(currentPortfolio, date)
    }
}