package org.temporal.example;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

/**
 * The GreetingWorker class is responsible for setting up and starting
 * the Temporal worker. A worker listens on a specific task queue
 * and executes workflows and activities assigned to it.
 */
public class GreetingWorker {

    public static void main(String[] args) {

        // Create a gRPC service stub for communicating with the Temporal server.
        // The 'newLocalServiceStubs' method connects to a local Temporal service.
        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();

        // Create a WorkflowClient to communicate with the Temporal server.
        WorkflowClient client = WorkflowClient.newInstance(service);

        // Create a WorkerFactory to manage workers.
        WorkerFactory factory = WorkerFactory.newInstance(client);

        // Create a Worker that listens on the specified task queue.
        Worker worker = factory.newWorker(Shared.TASK_QUEUE);

        // Register the implementation class of the GreetingWorkflow interface.
        worker.registerWorkflowImplementationTypes(GreetingWorkflowImpl.class);

        // Register the implementation class of the GreetingActivities interface.
        worker.registerActivitiesImplementations(new GreetingActivitiesImpl());

        // Start the WorkerFactory to begin polling the task queue and executing tasks.
        factory.start();
    }
}
