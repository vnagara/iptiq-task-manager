Full task description in _Task_Manager.pdf_

To run a program: 
    ```java -jar task-manager-1.0-SNAPSHOT.jar```
    
Expected execution result with current configs:

```
Adding duplicated task:	Task with the same PID already exists
Adding new task:	New task Task(pid=5, priority=MEDIUM) created
Adding over limit:	Capacity limit of 7 has been reached
Inserting LOW priority task:	Task with LOW priority was not inserted
Inserting MEDIUM priority task:	Task with MEDIUM priority was inserted instead of Task(pid=2, priority=LOW)
Inserting HIGH priority task:	Task with HIGH priority was inserted instead of Task(pid=0, priority=MEDIUM)
Trying to add FIFO:	Task Task(pid=9, priority=MEDIUM) was inserted by FIFO
Sorted by PID:	[Task(pid=3, priority=MEDIUM), Task(pid=4, priority=MEDIUM), Task(pid=5, priority=MEDIUM), Task(pid=6, priority=MEDIUM), Task(pid=7, priority=HIGH), Task(pid=8, priority=HIGH), Task(pid=9, priority=MEDIUM)]
Sorted by PRIORITY:	[Task(pid=3, priority=MEDIUM), Task(pid=4, priority=MEDIUM), Task(pid=5, priority=MEDIUM), Task(pid=6, priority=MEDIUM), Task(pid=9, priority=MEDIUM), Task(pid=8, priority=HIGH), Task(pid=7, priority=HIGH)]
Sorted by CREATED:	[Task(pid=3, priority=MEDIUM), Task(pid=4, priority=MEDIUM), Task(pid=5, priority=MEDIUM), Task(pid=6, priority=MEDIUM), Task(pid=8, priority=HIGH), Task(pid=9, priority=MEDIUM), Task(pid=7, priority=HIGH)]
Kill MEDIUM priority:	[Task(pid=8, priority=HIGH), Task(pid=7, priority=HIGH)]
Kill all result:	[]

Process finished with exit code 0
```