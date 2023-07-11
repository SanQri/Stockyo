import java.text.SimpleDateFormat
import java.util.*

class TradingDate {

    val date: Date
    val string: String
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")

    constructor(string: String) {
        this.string = string
        date = dateFormat.parse(string)
    }

    constructor(date: Date) {
        this.date = date
        string = dateFormat.format(date)
    }

    override fun hashCode(): Int {
        return string.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        val otherDate = other as? TradingDate ?: return false
        return string == otherDate.string
    }
}