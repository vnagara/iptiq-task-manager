interface TaskManagerInterface {

    enum class SortBy {
        CREATED, PRIORITY, ID
    }

    /**
    The default behaviour is that we can
    accept new processes till when there is capacity
    inside the Task Manager, otherwise we won’t accept
    any new process
     */
    fun add(task: Task)

    /**
     * killing and removing from the TM list
    the oldest one
     */
    fun addFifo(task: Task): Task?

    /**
     * if the new
    process passed in the add() call has a higher priority
    compared to any of the existing one, we remove the
    lowest priority that is the oldest, otherwise we skip it
     */
    fun addByPriority(task: Task): Boolean

    /**
     * Kill a task
     */
    fun kill(task: Task)

    fun killAll()

    /**
     * killing all processes with a specific priority
     */
    fun killGroup()

    /**
     * to list() all the
    running processes, sorting them by time of creation, priority or id
     */
    fun list(type: SortBy = SortBy.ID)
}