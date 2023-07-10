import tradingStrategy.config.BasicTradingStrategyConfig
import java.text.SimpleDateFormat

fun main(args: Array<String>) {
    val parser = FinancialDataParser(args[0])
    val data = parser.parse()
    data?.let {
        val dataProvider = StockDataProviderImpl(it)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val config = BasicTradingStrategyConfig(dataProvider,
            dateFormat.parse("2012-04-26"), dateFormat.parse("2023-04-26"))
        val portfolio = PortfolioBuilder.build(1000f, dateFormat.parse("2012-04-26"),
            listOf(Pair(0.6f, "VOO"), Pair(0.2f, "VXUS"), Pair(0.2f, "VTI")), dataProvider)
        val strategy = SimpleBuyStrategy(portfolio, config)
        strategy.run()
        val startValue = strategy.value(config.startDate)
        val endValue = strategy.value(config.endDate)
        print(startValue)
        print(endValue)
    }
}
