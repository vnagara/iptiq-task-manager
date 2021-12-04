private const val REASON = "Capacity limit $MAX_CAPACITY has been reached"

class CapacityLimitException(message: String) : IllegalArgumentException(message) {
    constructor() : this(REASON)
}
