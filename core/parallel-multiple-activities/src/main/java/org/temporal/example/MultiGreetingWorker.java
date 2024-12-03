package org.temporal.example;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

// The MultiGreetingWorker class is responsible for setting up and starting the worker
// that processes Temporal workflows and activities.
public class MultiGreetingWorker {
    public static void main(String[] args) {
        // Step 1: Create a WorkflowServiceStubs instance to communicate with the Temporal service.
        // This connects to a local Temporal server instance. It acts as the bridge to the Temporal backend.
        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();

        // Step 2: Create a WorkflowClient instance.
        // The WorkflowClient provides APIs for starting, managing, and interacting with workflows.
        // It uses the WorkflowServiceStubs to send and receive requests from the Temporal service.
        WorkflowClient client = WorkflowClient.newInstance(service);

        // Step 3: Create a WorkerFactory instance.
        // The WorkerFactory is responsible for creating and managing worker threads.
        // It initializes the workers that execute workflow and activity tasks.
        WorkerFactory factory = WorkerFactory.newInstance(client);

        // Step 4: Create a Worker and associate it with a specific task queue.
        // Workers poll the Temporal service for tasks from the specified task queue.
        // The task queue is used to match workflows and activities to their respective workers.
        Worker worker = factory.newWorker(Shared.TASK_QUEUE);

        // Step 5: Register workflow implementation classes with the worker.
        // The worker will use these classes to execute the workflows when tasks are received.
        worker.registerWorkflowImplementationTypes(MultiGreetingWorkflowImpl.class);

        // Step 6: Register activity implementation instances with the worker.
        // The worker will use these instances to execute the activities invoked by workflows.
        worker.registerActivitiesImplementations(new GreetingActivitiesImpl());

        // Step 7: Start the WorkerFactory.
        // This begins polling for tasks from the specified task queue and starts executing them.
        factory.start();
    }
}
