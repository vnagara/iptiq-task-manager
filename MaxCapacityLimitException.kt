private const val REASON = "Capacity limit of $MAX_CAPACITY has been reached"

class MaxCapacityLimitException(message: String) : IllegalArgumentException(message) {
    constructor() : this(REASON)
}
