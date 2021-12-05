class TestTaskManager(val taskManager: TaskManager) {

    fun populate() {
        taskManager.add(Task(1))
        taskManager.addFifo(Task(2))
        taskManager.addByPriority(Task(3, Task.Priority.LOW))
        taskManager.add(Task())
        taskManager.add(Task(5))
    }

    fun testAdd() {
        println("Adding new task...")
        val new = taskManager.add(Task())
        println("New task $new created")
    }

    fun testDuplicatePid() {
        try {
            println("Adding task duplicated task")
            val dupTask = Task(3)
            taskManager.add(dupTask)
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }

    private fun reachMaxCapacity() {
        try {
            while (true) taskManager.add(Task())
        } catch (e: MaxCapacityLimitException) {}
    }

    fun testCapAdd() {
        reachMaxCapacity()
        try {
            taskManager.add(Task())
        } catch (e: MaxCapacityLimitException) {
            println(e.message)
        }
    }

    fun testCapAddFifo() {
        reachMaxCapacity()
        Task.Priority.values().forEach { priority ->
            try {
                val result = taskManager.addFifo(Task(0, priority))
                println("Task $result with $priority priority was inserted")
            } catch (e: MaxCapacityLimitException) {
                println("Task with priority $priority was not inserted. ${e.message}")
            }
        }
    }

    fun testCapAddByPriority() {
        reachMaxCapacity()
        Task.Priority.values().forEach { priority ->
            val atMaxCapacity = taskManager.isMaxCapacity()
            val removedTask = taskManager.addByPriority(Task(0, priority))
            if (null != removedTask && atMaxCapacity) {
                println("Task with $priority priority was inserted instead of $removedTask")
            } else {
                if (!atMaxCapacity)
                println("Task with $priority priority was not inserted")
            }
        }
    }
}