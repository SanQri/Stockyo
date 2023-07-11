interface TradingStrategy {
    fun run()
    fun value(date: TradingDate): StockPeriodValue?
}

