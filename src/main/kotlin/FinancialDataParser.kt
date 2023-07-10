import java.io.File
import java.nio.file.Path

class FinancialDataParser(private val rootDirectoryPath: String) {

    fun parse(): Map<String, StockData>? {
        val stockData = mutableMapOf<String, StockData>()
        val prices = getTickers("Prices")
        val dividends = getTickers("Dividends")

        if (prices == null || dividends == null) {
            return null
        }

        for ((key, price) in prices) {
            val dividend = dividends[key] ?: continue
            val reader = FinDataReader(price, dividend)
            stockData[key] = reader.makeData()
        }

        return stockData
    }

    private fun getTickers(type: String): Map<String, File>? {
        val directory = Path.of(rootDirectoryPath, type).toFile()
        if (!directory.exists() || !directory.isDirectory) {
            print("${type} directory does not exist")
            return null
        }
        return getChildren(directory)
    }

    private fun getChildren(file: File): Map<String, File>? {
        val files = file.listFiles()?.filterNotNull()?.toTypedArray()
        return files?.associateBy { it.nameWithoutExtension }
    }

}