import java.util.*

interface StockDataProvider {
    fun averagePrice(ticker: String, day: Date): Float
    fun value(portfolio: Portfolio, day: Date): StockPeriodValue?
    fun value(ticker: String, day: Date): StockPeriodValue?
}

class StockDataProviderImpl(val data: Map<String, StockData>): StockDataProvider {

    override fun averagePrice(ticker: String, day: Date): Float {
//        value(portfolio = )
        return 0f
    }

    override fun value(portfolio: Portfolio, day: Date): StockPeriodValue? {
        return portfolio.entries.mapNotNull {
            data[it.ticker]?.values?.get(day)?.times(it.shares)
        }.reduceOrNull {
            a, b -> a + b
        }
    }
    override fun value(ticker: String, day: Date): StockPeriodValue? {
        return data[ticker]?.values?.get(day)
    }

}