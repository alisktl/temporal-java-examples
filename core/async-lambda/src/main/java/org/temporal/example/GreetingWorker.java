package org.temporal.example;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

// The GreetingWorker class is responsible for setting up and starting the Temporal worker.
// This worker listens for tasks from the Temporal service and executes workflows and activities accordingly.
public class GreetingWorker {

    public static void main(String[] args) {
        // Step 1: Create a WorkflowServiceStubs instance to communicate with the Temporal service.
        // This connects to a local Temporal server instance or a remote Temporal service.
        // It acts as the bridge between the application and the Temporal backend.
        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();

        // Step 2: Create a WorkflowClient instance.
        // The WorkflowClient provides APIs for starting, managing, and interacting with workflows.
        // It uses the WorkflowServiceStubs to communicate with the Temporal service.
        WorkflowClient client = WorkflowClient.newInstance(service);

        // Step 3: Create a WorkerFactory instance.
        // The WorkerFactory is responsible for creating and managing worker threads.
        // It initializes workers that poll for workflow and activity tasks.
        WorkerFactory factory = WorkerFactory.newInstance(client);

        // Step 4: Create a Worker and associate it with a specific task queue.
        // The worker listens to the "Shared.TASK_QUEUE" for incoming tasks.
        // This task queue is where workflows and activities will send tasks to be processed.
        Worker worker = factory.newWorker(Shared.TASK_QUEUE);

        // Step 5: Register the workflow implementation with the worker.
        // Registers the GreetingWorkflowImpl class, allowing it to handle workflow execution requests.
        worker.registerWorkflowImplementationTypes(GreetingWorkflowImpl.class);

        // Step 6: Register the activity implementation with the worker.
        // Registers the GreetingActivitiesImpl class, enabling it to handle activity execution requests.
        worker.registerActivitiesImplementations(new GreetingActivitiesImpl());

        // Step 7: Start the worker factory.
        // This starts the workers, allowing them to poll for tasks from the Temporal service.
        factory.start();

        System.out.println("Worker started. Listening for workflow and activity tasks...");
    }
}
