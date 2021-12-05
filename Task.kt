import java.lang.RuntimeException
import java.time.LocalDateTime

private const val ERR_MSG_NEGATIVE_PID = "PID can't be negative"
private const val ERR_MSG_PID_LOW_VALUE = "PID can't be lower than the last one (%d)"

data class Task(
    var pid: Int = 0, val priority: Priority = Priority.MEDIUM,
//    private val created: LocalDateTime = LocalDateTime.now()
) {

    enum class Priority {
        LOW, MEDIUM, HIGH
    }

    // Track last ID to create Task with default params
    companion object {
        private var lastId = 0
    }

    // Calculate new PID by incrementing by 1
    init {
        pid = when (pid) {
            in Int.MIN_VALUE until 0 -> throw IllegalArgumentException(ERR_MSG_NEGATIVE_PID)
            0 -> ++lastId
            // To avoid duplicate ID problem use only incremental ID's
            in 1..lastId -> throw IllegalArgumentException(ERR_MSG_PID_LOW_VALUE.format(lastId))
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