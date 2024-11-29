package org.temporal.example;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

// The GreetingWorker class is responsible for setting up and starting the worker that handles Temporal workflows and activities.
public class GreetingWorker {

    public static void main(String[] args) {
        // Step 1: Create a WorkflowServiceStubs instance to communicate with the Temporal service.
        // In this case, it connects to a local Temporal server instance.
        // This service will be used for making requests to the Temporal service.
        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();

        // Step 2: Create a WorkflowClient instance.
        // The WorkflowClient provides APIs for starting and interacting with workflows.
        // The client communicates with the Temporal service through the service stubs.
        WorkflowClient client = WorkflowClient.newInstance(service);

        // Step 3: Create a WorkerFactory instance.
        // The WorkerFactory is responsible for creating and managing worker threads.
        // These workers poll task queues to execute workflow and activity tasks.
        WorkerFactory factory = WorkerFactory.newInstance(client);

        // Step 4: Create a Worker that listens to a specific task queue.
        // The worker listens for workflow tasks and activity tasks from the specified queue.
        // In this case, the worker listens to the task queue defined by Shared.TASK_QUEUE.
        Worker worker = factory.newWorker(Shared.TASK_QUEUE);

        // Register the workflow implementation with the worker.
        // The worker will execute workflows of type GreetingWorkflowImpl.
        worker.registerWorkflowImplementationTypes(GreetingWorkflowImpl.class);

        // Register the activity implementations with the worker.
        // The worker will execute activities defined in GreetingActivitiesImpl.
        worker.registerActivitiesImplementations(new GreetingActivitiesImpl());

        // Step 5: Start the factory, which in turn starts the worker and begins listening for tasks.
        factory.start();
    }
}
