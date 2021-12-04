import java.lang.RuntimeException
import java.time.LocalDateTime

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
        var a = 4
        pid = when (pid) {
            0 -> ++lastId
            // To avoid duplicate ID problem use only incremental ID's
            in Int.MIN_VALUE..lastId -> throw RuntimeException("PID can't be lower than the last one ($lastId)")
            else -> {
                lastId = pid
                lastId
            }
        }
    }

    // Function by task requirements
    fun kill() {
        TaskManager.instance.kill(this)
    }
}