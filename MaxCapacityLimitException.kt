private const val REASON = "Trying to add over limit of $MAX_CAPACITY tasks"

class MaxCapacityLimitException(message: String) : IllegalArgumentException(message) {
    constructor() : this(REASON)
}
