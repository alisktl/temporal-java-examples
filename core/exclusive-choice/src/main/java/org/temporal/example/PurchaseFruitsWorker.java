package org.temporal.example;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

/**
 * This class sets up and starts a Temporal worker for processing workflows and activities.
 * It connects to the Temporal service, creates workers, and registers workflow and activity implementations.
 */
public class PurchaseFruitsWorker {

    public static void main(String[] args) {
        // Step 1: Create a WorkflowServiceStubs instance to communicate with the Temporal service.
        // In this case, it connects to a local Temporal server instance.
        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();

        // Step 2: Create a WorkflowClient instance, which provides APIs to start and interact with workflows.
        // The WorkflowClient is used to interact with the Temporal service and start workflows.
        WorkflowClient client = WorkflowClient.newInstance(service);

        // Step 3: Create a WorkerFactory that manages workers connected to the WorkflowClient.
        // The WorkerFactory is responsible for creating and managing workers that execute workflows and activities.
        WorkerFactory factory = WorkerFactory.newInstance(client);

        // Step 4: Create a worker that listens to a specific task queue.
        // Workers poll the task queue for workflow tasks and activity tasks.
        // In this case, the worker listens to a queue defined by Shared.TASK_QUEUE.
        Worker worker = factory.newWorker(Shared.TASK_QUEUE);

        // Step 5: Register the workflow implementation class with the worker.
        // The worker uses this class to execute workflow logic when tasks arrive in the task queue.
        worker.registerWorkflowImplementationTypes(PurchaseFruitsWorkflowImpl.class);

        // Step 6: Register the activity implementation classes with the worker.
        // Activities are individual units of work within a workflow, and this specifies
        // which class contains the activity logic to be executed.
        worker.registerActivitiesImplementations(new OrderFruitsActivitiesImpl());

        // Step 7: Start the WorkerFactory.
        // This begins polling for tasks and executing workflows.
        // Once started, the worker will listen for incoming tasks in the task queue and start executing workflows.
        factory.start();
    }
}
