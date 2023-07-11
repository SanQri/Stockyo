import java.io.File
import java.util.*

class FinDataReader(val pricesFile: File, val dividendsFile: File) {

    fun makeData(): StockData {
        val pricesLines = pricesFile.readLines()
        val ticker = pricesFile.nameWithoutExtension
        val values = HashMap<TradingDate, StockPeriodValue>()
        val dividends = HashMap<TradingDate, Float>()

        for (i in 1 until pricesLines.size) {
            val line = pricesLines[i]
            val data = parseStockPriceData(line)
            values[data.date] = data.value
        }


        val dividendsLines = dividendsFile.readLines()
        for (i in 1 until dividendsLines.size) {
            val line = dividendsLines[i]
            val data = parseStockDividendData(line)
            dividends[data.date] = data.amount
        }

        return StockDataImpl(ticker, values, dividends)
    }

    fun parseStockPriceData(csvLine: String): StockPriceDataSample {
        val values = csvLine.split(",") // Split the line by commas

        val date = TradingDate(values[0])
        print(csvLine)
        val open = values[1].toFloat()
        val high = values[2].toFloat()
        val low = values[3].toFloat()
        val close = values[4].toFloat()
        val value = StockPeriodValue(open, high, low, close)

        return StockPriceDataSample(date, value)
    }
    fun parseStockDividendData(csvLine: String): StockDividendDataSample {
        val values = csvLine.split(",") // Split the line by commas

        val date = TradingDate(values[0])
        val amount = values[1].toFloat()

        return StockDividendDataSample(date, amount)
    }

}

