/*
 ##Task Manager

We want the Task Manager to expose the following
functionality
Add a process
List running processes
Kill/KillGroup/KillAll
*/


fun main(args: Array<String>) {
    println("Hello World!")
    val a = when (-1) {
        1 -> 2
        1 -> 1
        else -> 3
    }
    val tm = TaskManager.instance.list()
    println(a)

}