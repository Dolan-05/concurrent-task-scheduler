package com.liam.taskscheduler;

public class Main {
    public static void main(String[] args) {
        
        Task task1 = new Task(1, "Generate Report", Priority.HIGH);
        Task task2 = new Task(2, "Send Email", Priority.LOW);
        Task task3 = new Task(3, "Process Data", Priority.MEDIUM);

        TaskScheduler scheduler = new TaskScheduler();

        scheduler.addTask(task1);
        scheduler.addTask(task2);
        scheduler.addTask(task3);

        
        while(scheduler.hasTask()){
        Task task = scheduler.getNextTask();
        scheduler.executeTask(task);
        }
        scheduler.shutdown();

    }
}