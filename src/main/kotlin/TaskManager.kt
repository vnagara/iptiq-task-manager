import TaskManagerInterface.SortBy

internal const val MAX_CAPACITY = 7 //Should be greater than 0

class TaskManager private constructor() : TaskManagerInterface {

    private val tasks = mutableListOf<Task>()

    companion object {
        // As we keep list in memory and have no threads then by YAGNI we don't create
        // synchronized object and use an unique single instance
        val instance: TaskManager = TaskManager()
    }

    private fun checkForUniquePid(task: Task) {
        if (tasks.any { it.pid == task.pid }) throw IllegalArgumentException(
                "Task with the same PID already exists"
        )
    }

    // For illustration purposes use exception, return Boolean would suffice
    override fun add(task: Task): Task {
        if (isMaxCapacity()) throw MaxCapacityLimitException()
        checkForUniquePid(task)
        tasks.add(task)
        return task
    }

    override fun addFifo(task: Task): Task? {
        checkForUniquePid(task)
        tasks.add(task)
        if (isMaxCapacity()) {
            return tasks.removeFirst()
        }
        return null
    }

    /**
     * @return removed Task if it was killed. Null otherwise
     */
    override fun addByPriority(task: Task): Task? {
        checkForUniquePid(task)
        if (isMaxCapacity()) {
            val lowestTask = list(SortBy.PRIORITY).first()
            if (lowestTask.priority >= task.priority) return null
            tasks.add(task)
            kill(lowestTask)
            return lowestTask
        }
        tasks.add(task)
        return null
    }

    override fun get(pid: Int): Task? {
        return tasks.find { task -> pid == task.pid }
    }

    override fun create(pid: Int?, priority: Task.Priority): Task {
        if (null != pid) return Task(pid, priority)
        return if (tasks.isEmpty()) Task(FIRST_PID, priority)
        else Task(list(SortBy.PID).last().pid+1, priority)
    }

    fun isMaxCapacity(): Boolean {
        return tasks.size >= MAX_CAPACITY
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
    
    override fun list(sortBy: SortBy): List<Task> {
        return when (sortBy) {
            SortBy.CREATED -> tasks
            // We assuming that list is small in memory so copy it
            SortBy.PID -> tasks.sortedBy { task -> task.pid }
            SortBy.PRIORITY -> tasks.sortedBy { task -> task.priority }
        }
    }
}