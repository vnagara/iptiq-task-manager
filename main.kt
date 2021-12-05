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
    val test = TestTaskManager(taskManager)
    test.populate()
    test.testDuplicatePid()
    test.testAdd()
    test.testCapAdd()
    test.testCapAddByPriority()
}