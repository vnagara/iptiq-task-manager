interface TaskManagerInterface {

    enum class SortBy {
        CREATED, PRIORITY, PID
    }

    /**
    The default behaviour is that we can
    accept new processes till when there is capacity
    inside the Task Manager, otherwise we won’t accept
    any new process
     */
    fun add(task: Task): Task

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

     * @return Removed Task if it was substituted. Null otherwise
     */
    fun addByPriority(task: Task): Task?

    /**
     * Find task by PID
     */
    fun get(pid: Int): Task?

    /**
     * Kill a task
     */
    fun kill(task: Task): Boolean

    fun killAll()

    /**
     * killing all processes with a specific priority
     */
    fun killGroup(priority: Task.Priority): Boolean

    /**
     * to list() all the
    running processes, sorting them by time of creation, priority or id
     */
    fun list(sortBy: SortBy = SortBy.CREATED): List<Task>
}