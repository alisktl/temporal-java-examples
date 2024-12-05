package org.temporal.example;

import io.temporal.client.ActivityCompletionClient;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

// The GreetingWorker class is responsible for setting up and starting the worker
// that processes Temporal workflows and activities. This worker listens for tasks from the Temporal service
// and executes workflows and activities accordingly.
public class GreetingWorker {

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

        // Step 5: Register the workflow implementation with the worker.
        // This registers the GreetingWorkflowImpl class, so it can be used to handle incoming workflow tasks.
        worker.registerWorkflowImplementationTypes(GreetingWorkflowImpl.class);

        // Step 6: Register the activities implementation with the worker.
        // This registers the GreetingActivitiesImpl class to handle activity tasks.
        ActivityCompletionClient completionClient = client.newActivityCompletionClient();
        worker.registerActivitiesImplementations(new GreetingActivitiesImpl(completionClient));

        // Step 7: Start the worker factory to begin processing workflows and activities.
        // This starts the worker to listen for tasks on the assigned task queue.
        factory.start();
    }
}
