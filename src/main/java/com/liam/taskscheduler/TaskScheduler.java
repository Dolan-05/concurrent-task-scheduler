package com.liam.taskscheduler;

import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class TaskScheduler {
    private PriorityBlockingQueue<Task> taskQueue;
    private ExecutorService executor;
    private Comparator<Task> taskComparator = 
    (a, b) -> b.getPriority().ordinal() - a.getPriority().ordinal();

    public TaskScheduler(){
        this.taskQueue = new PriorityBlockingQueue<>(11, taskComparator);

        this.executor = Executors.newFixedThreadPool(3);
    }

    public void addTask(Task task){
        taskQueue.add(task);
    }
    public Task getNextTask(){
        return taskQueue.poll();
    }
    public void executeTask(Task task){
        executor.submit(() -> {
            task.setStatus(TaskStatus.RUNNING);
            
            
            try{
                int duration = ThreadLocalRandom.current().nextInt(1000, 5001);
                System.out.println("Starting: " + task.getName() + " on " + Thread.currentThread().getName() + " | Duration: " + duration + "ms");
                Thread.sleep(duration);
                task.setStatus(TaskStatus.COMPLETED);
                System.out.println(task.getName() + " is Complete");
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
                task.setStatus(TaskStatus.FAILED);
                System.out.println(task.getName() + " failed");
            }

            

        });

    }
    public void shutdown(){
        executor.shutdown();
    }
    public boolean awaitTermination() throws InterruptedException {
        return executor.awaitTermination(20, TimeUnit.SECONDS);
    }

    public boolean hasTask(){
        return !taskQueue.isEmpty();
    }
    
}
