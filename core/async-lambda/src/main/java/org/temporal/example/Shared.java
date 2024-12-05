package org.temporal.example;

/**
 * This interface defines shared constants used across the Temporal application.
 * It acts as a central place to store reusable values, ensuring consistency across the codebase.
 */
public interface Shared {

    /**
     * TASK_QUEUE represents the name of the task queue that workers will listen to for workflow tasks.
     * <p>
     * - A task queue is a logical queue used by Temporal to route workflow and activity tasks to workers.
     * - Workers poll this queue to execute workflows or activities associated with it.
     */
    final String TASK_QUEUE = "async-lambda-task-queue";
}
