/*
 ##Task Manager

We want the Task Manager to expose the following
functionality
Add a process
List running processes
Kill/KillGroup/KillAll
*/


fun main(args: Array<String>) {
    val taskManager = TaskManager.instance
    TestTaskManager(taskManager).run {
        populate()
        testDuplicatePid()
        testAdd()
        testCapAdd()
        testCapAddByPriority()
        testCapAddFifo()
        taskManager.get(7)?.kill()
        taskManager.addByPriority(taskManager.create(7, Task.Priority.HIGH))
        testList()
        testKillGroup(Task.Priority.MEDIUM)
        testKillAll()
    }
}