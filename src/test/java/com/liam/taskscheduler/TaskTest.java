package com.liam.taskscheduler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test 
    public void newTaskShouldStartWaiting(){
        Task task = new Task(1, "Test task", Priority.HIGH);

        assertEquals(TaskStatus.WAITING, task.getStatus());
    }
    
}
