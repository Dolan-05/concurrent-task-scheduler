package com.liam.taskscheduler;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class TaskSchedulerTest {
    
    @Test
    public void higherPriorityTaskShouldComeFirst(){
        Task task2 = new Task(3, "Priority Test", Priority.LOW);
        Task task1 = new Task(2, "Priority Test", Priority.HIGH );
        

        TaskScheduler scheduler = new TaskScheduler();

        scheduler.addTask(task2);
        scheduler.addTask(task1);

        Task nextTask = scheduler.getNextTask();

        assertEquals(Priority.HIGH, nextTask.getPriority());
        
    }

    @Test
    public void schedulerWithTaskShouldHaveTask(){

        Task testTask = new Task(1, "TaskTest", Priority.HIGH);

        TaskScheduler scheduler = new TaskScheduler();

        scheduler.addTask(testTask);

        assertTrue(scheduler.hasTask());
    }
    @Test
    public void emptySchedulerShouldHaveNoTasks(){
        
        TaskScheduler scheduler = new TaskScheduler();

        assertFalse(scheduler.hasTask());

    }
    @Test
    public void removingLastTaskShouldEmptyScheduler(){

        Task testTask = new Task(1, "TaskTest", Priority.HIGH);

        TaskScheduler scheduler = new TaskScheduler();

        scheduler.addTask(testTask);

        scheduler.getNextTask();

        assertFalse(scheduler.hasTask());

    }
    @Test
    public void tasksShouldBeRetrievedInPriorityOrder(){

        Task testTask1 = new Task(1, "TaskTest1", Priority.LOW);
        Task testTask2 = new Task(2, "TaskTest2", Priority.MEDIUM);
        Task testTask3 = new Task(3, "TaskTest3", Priority.HIGH);

        TaskScheduler scheduler = new TaskScheduler();

        scheduler.addTask(testTask1);
        scheduler.addTask(testTask2);
        scheduler.addTask(testTask3);

        assertEquals(testTask3, scheduler.getNextTask());
        assertEquals(testTask2, scheduler.getNextTask());
        assertEquals(testTask1, scheduler.getNextTask());
    }
    @Test
    public void excecuteTaskShouldComplete() throws InterruptedException{
        Task testTask1 = new Task(1, "TaskTest1", Priority.LOW);

        TaskScheduler scheduler = new TaskScheduler();

        scheduler.executeTask(testTask1);

        scheduler.shutdown();

        assertTrue(scheduler.awaitTermination());
        assertEquals(TaskStatus.COMPLETED, testTask1.getStatus());
    }
    @Test
    public void multipleTasksShouldComplete() throws InterruptedException{
        Task testTask1 = new Task(1, "TaskTest1", Priority.LOW);
        Task testTask2 = new Task(2, "TaskTest2", Priority.MEDIUM);
        Task testTask3 = new Task(3, "TaskTest3", Priority.HIGH);
        Task testTask4 = new Task(4, "TaskTest4", Priority.LOW);
        Task testTask5 = new Task(5, "TaskTest5", Priority.MEDIUM);

        TaskScheduler scheduler = new TaskScheduler();

        scheduler.executeTask(testTask1);
        scheduler.executeTask(testTask2);
        scheduler.executeTask(testTask3);
        scheduler.executeTask(testTask4);
        scheduler.executeTask(testTask5);

        scheduler.shutdown();

        assertTrue(scheduler.awaitTermination());
        assertEquals(TaskStatus.COMPLETED, testTask1.getStatus());
        assertEquals(TaskStatus.COMPLETED, testTask2.getStatus());
        assertEquals(TaskStatus.COMPLETED, testTask3.getStatus());
        assertEquals(TaskStatus.COMPLETED, testTask4.getStatus());
        assertEquals(TaskStatus.COMPLETED, testTask5.getStatus());

    }
}
