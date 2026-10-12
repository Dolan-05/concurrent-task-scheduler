# Concurrent Task Scheduler

A Java project I built to get a better understanding of multithreading, concurrency and how tasks are scheduled and executed.

The application allows tasks to be assigned different priorities and executed concurrently using a fixed thread pool.

## Features

- Tasks can be assigned LOW, MEDIUM or HIGH priority.
- Uses a `PriorityBlockingQueue` to manage tasks in priority order.
- Uses `ExecutorService` with a fixed pool of 3 threads.
- Tracks task statuses: WAITING, RUNNING, COMPLETED and FAILED.
- Simulates different execution times for tasks.
- Supports graceful shutdown and waiting for tasks to finish.
- Includes 8 JUnit tests to check the scheduler's functionality.

## Technologies Used

- Java 17
- Maven
- JUnit 5
- Java Concurrency API

## How It Works

Each task has an ID, name, priority and status.

Tasks are added to a priority queue, where higher-priority tasks are retrieved first. The scheduler then submits them to an `ExecutorService`, which manages a pool of 3 worker threads.

If more than 3 tasks are submitted, the remaining tasks wait until a worker becomes available.

When a task starts executing, its status changes from WAITING to RUNNING. Once it finishes, its status changes to COMPLETED.

Tasks have randomly generated execution times between 1 and 5 seconds to simulate different workloads.

Because tasks run concurrently, they may finish in a different order from the order they were submitted.

## Testing

I used JUnit 5 to test the main functionality of the application, including:

- Checking the initial status of a task.
- Checking whether the scheduler contains tasks.
- Retrieving and removing tasks from the queue.
- Making sure higher-priority tasks are retrieved first.
- Checking that individual tasks complete execution.
- Checking that multiple tasks complete successfully.

All 8 tests are currently passing.

## Running the Project

Requires Java 17 and Maven.

To compile:

```bash
mvn clean compile
```

To run the tests:

```bash
mvn test
```

To run the application:

```bash
java -cp target/classes com.liam.taskscheduler.Main
```

