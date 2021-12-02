import java.lang.RuntimeException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Task(
    var pid: Int = 0, val priority: Priority = Priority.MEDIUM,
    private val created: LocalDateTime = LocalDateTime.now()) {

    enum class Priority {
        LOW, MEDIUM, HIGH
    }

    // Track last ID to create Task with default params
    companion object {
        private var lastId = 0
    }

    // Calculate new PID by incrementing by 1
    init {
        pid = when (this.pid) {
            0 -> ++lastId
            // To avoid duplicate ID problem use only incremental ID's
            in Int.MIN_VALUE..lastId -> throw RuntimeException("PID can't be lower than the last one ($lastId)")
            else -> {
                lastId = this.pid
                pid
            }
        }
    }
}

class TaskManager {
    enum class SortBy {
        CREATED, PRIORITY, ID
    }

    fun create(): Task {
        val a = Task();
        return a;
    }

    /**
     * list() all the
    running processes
    sorting them by time of creation
    (implicitly we can consider it the time in which has
    been added to the TM), priority or id
     */
    fun list(type: SortBy = SortBy.ID) {

    }
}