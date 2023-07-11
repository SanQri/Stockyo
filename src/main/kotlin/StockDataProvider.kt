interface StockDataProvider {
    fun averagePrice(ticker: String, day: TradingDate): Float?
    fun averagePrice(portfolio: Portfolio, day: TradingDate): Float?
    fun value(portfolio: Portfolio, day: TradingDate): StockPeriodValue?
    fun value(ticker: String, day: TradingDate): StockPeriodValue?
    fun fractionOfEntry(ticker: String, portfolio: Portfolio, day: TradingDate): Float?
}

class StockDataProviderImpl(val data: Map<String, StockData>): StockDataProvider {

    override fun averagePrice(ticker: String, day: TradingDate): Float? {
        val dataSample = data[ticker] ?: return null
        val value = dataSample.values.get(day) ?: return null
//        val value = data[ticker]?.values?.get(day) ?: return null
        return (value.high + value.low) / 2f
    }
    override fun fractionOfEntry(ticker: String, portfolio: Portfolio, day: TradingDate): Float? {
        val index = portfolio.entries.indexOfFirst { it.ticker == ticker }
        if (index == -1) { return null }
        val shares = portfolio.entries[index].shares
        val totalPrice = averagePrice(portfolio, day) ?: return null
        val entryPrice = averagePrice(ticker, day)?.times(shares)
        return entryPrice?.div(totalPrice)
    }
    override fun averagePrice(portfolio: Portfolio, day: TradingDate): Float? {
        val items = portfolio.entries.mapNotNull {
            val p = averagePrice(it.ticker, day)
            val result = p?.times(it.shares)
            result
        }
        if (items.size < 1) { return null }
        return items.sum()
    }

    override fun value(portfolio: Portfolio, day: TradingDate): StockPeriodValue? {
        return portfolio.entries.mapNotNull {
            data[it.ticker]?.values?.get(day)?.times(it.shares)
        }.reduceOrNull {
            a, b -> a + b
        }
    }
    override fun value(ticker: String, day: TradingDate): StockPeriodValue? {
        return data[ticker]?.values?.get(day)
    }

}