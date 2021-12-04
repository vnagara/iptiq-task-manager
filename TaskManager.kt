internal const val MAX_CAPACITY = 100

class TaskManager private constructor() : TaskManagerInterface {

    // Tracking the first index for fifo add
    private val startIndex: Int = 0

    private val tasks = mutableListOf<Task>()

    // As we keep list in memory then by YAGNI we don't create synchronized object or coroutines but use
    // an unique single instance
    companion object {
        val instance: TaskManager = TaskManager()
    }

    private fun checkForUniquePid(task: Task) {
        if (tasks.any { it.pid == task.pid }) throw IllegalArgumentException(
                "Task with the same PID already exists"
        )
    }

    private fun isMaxCapacity(): Boolean {
        return tasks.size >= MAX_CAPACITY
    }

    override fun add(task: Task) {
        checkForUniquePid(task)
        if (isMaxCapacity()) throw CapacityLimitException()
        tasks.add(task)
    }

    override fun addFifo(task: Task): Task? {
        checkForUniquePid(task)
        tasks.add(task)
        return tasks.removeFirstOrNull()
    }

    private fun getTheLowestPriorityTask(): Task? {
        var lowest: Task
        if (tasks.firstOrNull() == null) return null
        lowest = tasks.first()
        tasks.forEach { if (it.priority > lowest.priority) lowest = it }
        return lowest
    }

    override fun addByPriority(task: Task): Boolean {
        checkForUniquePid(task)
        if (isMaxCapacity()) {
            val lowestTask = getTheLowestPriorityTask()
            if (lowestTask != null && lowestTask.priority >= task.priority) return false
            tasks.remove(lowestTask)
        }
        return tasks.add(task)
    }

    override fun killGroup() {
        TODO("Not yet implemented")
    }

    override fun kill(task: Task) {
        TODO("Not yet implemented")
    }

    override fun killAll() {
        TODO("Not yet implemented")
    }

    /**
     * list() all the
    running processes
    sorting them by time of creation
    (implicitly we can consider it the time in which has
    been added to the TM), priority or id
     */
    override fun list(type: TaskManagerInterface.SortBy) {
        MAX_CAPACITY
    }

}