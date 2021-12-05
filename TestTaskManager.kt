class TestTaskManager(val taskManager: TaskManager) {

    fun populate() {
        try {
            taskManager.add(taskManager.create())
            taskManager.addFifo(Task(1))
            taskManager.addByPriority(Task(2, Task.Priority.LOW))
            taskManager.add(taskManager.create())
            taskManager.add(taskManager.create())
        } catch (e: MaxCapacityLimitException) {
            println(e.message)
        }
    }

    fun testAdd() {
        print("Adding new task:\t")
        try {
            val new = taskManager.add(taskManager.create())
            println("New task $new created")
        } catch (e: MaxCapacityLimitException) {
            println(e.message)
        }
    }

    fun testDuplicatePid() {
        try {
            print("Adding duplicated task:\t")
            val dupTask = Task(3)
            taskManager.add(dupTask)
            println("Duplicated task $dupTask was added")
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }

    private fun reachMaxCapacity() {
        try {
            while (true) taskManager.add(taskManager.create())
        } catch (e: MaxCapacityLimitException) {}
    }

    fun testCapAdd() {
        reachMaxCapacity()
        print("Adding over limit:\t")
        try {
            taskManager.add(taskManager.create())
        } catch (e: MaxCapacityLimitException) {
            println(e.message)
        }
    }

    fun testCapAddFifo() {
        reachMaxCapacity()
        print("Trying to add FIFO:\t")
        val task = taskManager.create()
        taskManager.addFifo(task)
        println("Task $task was inserted by FIFO")
    }

    fun testCapAddByPriority() {
        reachMaxCapacity()
        Task.Priority.values().forEach { priority ->
            print("Inserting $priority priority task:\t")
            if (!taskManager.isMaxCapacity()) {
                taskManager.addByPriority(taskManager.create(null, priority))
                println("Task with $priority priority was inserted")
            } else {
                val removedTask = taskManager.addByPriority(taskManager.create(null, priority))
                if (null == removedTask) {
                    println("Task with $priority priority was not inserted")
                } else println("Task with $priority priority was inserted instead of $removedTask")
            }
        }
    }

    fun testKillGroup(priority: Task.Priority) {
        taskManager.killGroup(priority)
        println("Kill $priority priority:\t" + taskManager.list())
    }

    fun testKillAll() {
        taskManager.killAll()
        println("Kill all result:\t" + taskManager.list())

    }

    fun testList() {
        TaskManagerInterface.SortBy.values().forEach {
            print("Sorted by $it:\t")
            println(taskManager.list(it))
        }
    }
}