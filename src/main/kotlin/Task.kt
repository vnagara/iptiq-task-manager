import java.lang.RuntimeException
import java.time.LocalDateTime

internal const val FIRST_PID = 0
private const val ERR_MSG_NEGATIVE_PID = "PID can't be negative"

data class Task(
    val pid: Int, val priority: Priority = Priority.MEDIUM,
) {

    enum class Priority {
        LOW, MEDIUM, HIGH
    }

    init {
        // Put a restriction for example
        if (pid < FIRST_PID) throw IllegalArgumentException(ERR_MSG_NEGATIVE_PID)
    }

    // Function by task requirements
    fun kill() {
        TaskManager.instance.kill(this)
    }
}