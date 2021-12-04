internal const val MAX_CAPACITY = 100

class TaskManager private constructor() : TaskManagerInterface {

    private val tasks = mutableListOf<Task>()

    // As we keep list in memory then by YAGNI we don't create synchronized object or coroutines but use just
    // an unique single instance
    companion object {
        val instance: TaskManager = TaskManager()
    }

    override fun add(task: Task) {
        tasks.add(task)
    }

    override fun addFifo(task: Task) {
        TODO("Not yet implemented")
    }

    override fun addByPriority(task: Task) {
        TODO("Not yet implemented")
    }

    override fun kill(task: Task) {
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