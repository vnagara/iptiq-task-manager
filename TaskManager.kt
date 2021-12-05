import TaskManagerInterface.SortBy

internal const val MAX_CAPACITY = 7

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

    fun isMaxCapacity(): Boolean {
        return tasks.size >= MAX_CAPACITY
    }

    // For show purposes use exception, return Boolean could suffice
    override fun add(task: Task): Task {
        checkForUniquePid(task)
        if (isMaxCapacity()) throw MaxCapacityLimitException()
        tasks.add(task)
        return task
    }

    override fun addFifo(task: Task): Task? {
        checkForUniquePid(task)
        tasks.add(task)
        return tasks.removeFirstOrNull()
    }

    private fun getTheLowestPriorityTaskOrNull(): Task? {
        var lowest: Task?
        lowest = tasks.firstOrNull()
        if (lowest == null) return null
        if (lowest.priority == Task.Priority.LOW) return lowest
        tasks.forEach {
            if (it.priority == Task.Priority.LOW) return it  //finish here, found the lowest
            if (it.priority < lowest!!.priority) lowest = it }
        return lowest
    }

    /**
     * @return Removed Task if it was substituted. Null otherwise
     */
    override fun addByPriority(task: Task): Task? {
        checkForUniquePid(task)
        if (isMaxCapacity()) {
            val lowestTask = getTheLowestPriorityTaskOrNull()
            if (lowestTask != null && lowestTask.priority >= task.priority) return null
            tasks.remove(lowestTask)
            return lowestTask
        }
        tasks.add(task)
        return null
    }

    override fun get(pid: Int): Task? {
        return tasks.find { task -> pid == task.pid }
    }

    override fun killGroup(priority: Task.Priority): Boolean {
        return tasks.removeAll { task -> priority == task.priority }
    }

    override fun kill(task: Task): Boolean {
        return tasks.remove(task)
    }

    override fun killAll() {
        tasks.clear()
    }

    /**
     * list() all the
    running processes
    sorting them by time of creation
    (implicitly we can consider it the time in which has
    been added to the TM), priority or id
     */
    override fun list(sortBy: SortBy): List<Task> {
        return when (sortBy) {
            SortBy.CREATED -> tasks
            // We assuming that list is small in memory so copy it
            SortBy.PID -> tasks.sortedBy { task -> task.pid }
            SortBy.PRIORITY -> tasks.sortedBy { task -> task.priority }
        }
    }
}