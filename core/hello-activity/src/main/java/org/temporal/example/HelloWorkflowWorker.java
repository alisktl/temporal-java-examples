package org.temporal.example;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

/**
 * This class sets up a Temporal worker to listen for workflow tasks and execute workflow implementations.
 */
public class HelloWorkflowWorker {

    public static void main(String[] args) {
        // Step 1: Create a WorkflowServiceStubs instance to communicate with the Temporal service.
        // In this case, it connects to a local Temporal server instance.
        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();

        // Step 2: Create a WorkflowClient instance, which provides APIs to start and interact with workflows.
        WorkflowClient client = WorkflowClient.newInstance(service);

        // Step 3: Create a WorkerFactory that manages workers connected to the WorkflowClient.
        WorkerFactory factory = WorkerFactory.newInstance(client);

        // Step 4: Create a worker that listens to a specific task queue.
        // Workers poll the task queue for workflow tasks and activity tasks.
        Worker worker = factory.newWorker(Shared.TASK_QUEUE);

        // Step 5: Register the workflow implementation class with the worker.
        // The worker uses this class to execute workflow logic when tasks arrive in the task queue.
        worker.registerWorkflowImplementationTypes(HelloWorkflowImpl.class);

        worker.registerActivitiesImplementations(new HelloActivitiesImpl());

        // Step 6: Start the WorkerFactory.
        // This begins polling for tasks and executing workflows.
        factory.start();
    }
}
