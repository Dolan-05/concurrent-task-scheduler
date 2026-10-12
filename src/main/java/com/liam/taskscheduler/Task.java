package com.liam.taskscheduler;

public class Task {
    
private int id;
private String name;
private Priority priority;
private TaskStatus status;
private int duration;

public Task(int id, String name, Priority priority){
    this.id = id;
    this.name = name;
    this.priority = priority;
    this.status = TaskStatus.WAITING;
}

public int getId(){
    return id;
}
public String getName(){
    return name;
}
public Priority getPriority(){
    return priority;
}
public TaskStatus getStatus(){
    return status;
}
public void setStatus(TaskStatus status){
    this.status = status;
}

}
