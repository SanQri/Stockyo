class PortfolioBuilder {

    companion object{
        fun build(price: Float, date: TradingDate,
                  stocks: List<Pair<Float, String>>,
                  dataProvider: StockDataProvider): Portfolio {
            val total = stocks.map { it.first }.sum()
            val entries = mutableListOf<PortfolioEntry>()
            for ((share, ticker) in stocks) {
                val value = dataProvider.value(ticker, date) ?: continue
                val amount = share / total
                val moneyForTheStock = price * amount
                val avgPrice = (value.high + value.low) / 2f
                val sharesAmount = moneyForTheStock / avgPrice
                val entry = PortfolioEntry(sharesAmount, ticker)
                entries.add(entry)
            }
            return Portfolio(entries.toTypedArray())
        }
    }

}