import java.util.*

interface StockData {
    val ticker: String
    val values: Map<Date, StockPeriodValue>
    val dividends: Map<Date, Float>
}

class StockDataImpl(
    override val ticker: String,
    override val values: Map<Date, StockPeriodValue>,
    override val dividends: Map<Date, Float>): StockData { }

class StockPriceDataSample(
    val date: Date,
    val value: StockPeriodValue) { }

class StockDividendDataSample(
    val date: Date,
    val amount: Float) { }

class StockPeriodValue(
    val open: Float,
    val high: Float,
    val low: Float,
    val close: Float) {

    operator fun plus(value: StockPeriodValue): StockPeriodValue {
        return StockPeriodValue(
            open + value.open,
            high + value.high,
            low + value.low,
            close + value.close
        )
    }
    operator fun times(value: Float): StockPeriodValue {
        return StockPeriodValue(
            open * value,
            high * value,
            low * value,
            close * value
        )
    }

}
