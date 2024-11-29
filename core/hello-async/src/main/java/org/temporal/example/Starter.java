package org.temporal.example;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;

/**
 * This class is responsible for starting the workflow execution.
 * It sets up the necessary Temporal client, workflow options,
 * and submits a workflow to be executed.
 */
public class Starter {

    public static void main(String[] args) throws Exception {
        // Step 1: Create a WorkflowServiceStubs instance to communicate with the Temporal service.
        // This connects to a local Temporal server instance for testing purposes.
        // In a production environment, you would connect to a remote Temporal service.
        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();

        // Step 2: Create a WorkflowClient instance.
        // The WorkflowClient is used to interact with the Temporal service.
        // It allows you to start workflows, query their status, and retrieve results.
        WorkflowClient client = WorkflowClient.newInstance(service);

        // Step 3: Create WorkflowOptions to configure the workflow execution.
        // These options define the configuration for the workflow execution, such as its unique workflow ID and task queue.
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setWorkflowId("hello-async-task-id")  // Set a unique identifier for this workflow instance.
                .setTaskQueue(Shared.TASK_QUEUE)       // Set the task queue that the worker will listen to. This must match the worker's queue.
                .build();

        // Step 4: Create a workflow stub.
        // The workflow stub is a client-side representation of the workflow.
        // This allows us to call methods defined in the workflow interface (GreetingWorkflow) as if they were local method calls.
        GreetingWorkflow workflow = client.newWorkflowStub(GreetingWorkflow.class, options);

        // Step 5: Start the workflow and wait for the result.
        // Call the getGreeting method of the workflow, passing the name from the command-line arguments.
        // The workflow will be executed by the worker listening to the task queue.
        System.out.println(workflow.getGreeting(args[0]));

        // Step 6: Exit the program after the workflow has completed.
        System.exit(0);
    }
}
