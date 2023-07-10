import java.util.*

interface TradingStrategy {
    fun run()
    fun value(date: Date): StockPeriodValue?
}

