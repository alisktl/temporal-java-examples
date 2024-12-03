package org.temporal.example;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.client.WorkflowStub;
import io.temporal.serviceclient.WorkflowServiceStubs;

/**
 * This class is responsible for starting the Temporal workflow execution.
 * It demonstrates how to set up a Temporal client, configure workflow options,
 * and interact with the workflow (start, signal, and retrieve the result).
 */
public class Starter {

    // A constant defining the unique Workflow ID for this workflow instance.
    public static final String WORKFLOW_ID = "hello-await-task-id";

    public static void main(String[] args) throws Exception {
        // Step 1: Create a WorkflowServiceStubs instance to communicate with the Temporal service.
        // This connects to a local Temporal server for development/testing.
        // For production, configure the connection to point to a remote Temporal service.
        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();

        // Step 2: Create a WorkflowClient instance.
        // This is the main entry point for interacting with the Temporal service.
        WorkflowClient client = WorkflowClient.newInstance(service);

        // Step 3: Define workflow options for this workflow instance.
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setWorkflowId(WORKFLOW_ID)         // Unique ID to identify the workflow instance.
                .setTaskQueue(Shared.TASK_QUEUE)    // Specify the task queue the worker listens to.
                .build();

        // Step 4: Create a workflow stub for the GreetingWorkflow interface.
        // The stub allows interaction with the workflow methods (e.g., starting, signaling).
        GreetingWorkflow workflow = client.newWorkflowStub(GreetingWorkflow.class, options);

        // Step 5: Start the workflow execution by invoking the 'getGreeting' method asynchronously.
        WorkflowClient.start(workflow::getGreeting);

        // Step 6: Send a signal to the running workflow to pass the name.
        workflow.waitForName(args[0]);  // The name is taken as the first command-line argument.

        // Step 7: Retrieve the result of the workflow execution using an untyped stub.
        // This is an alternative way to interact with workflows if you know the Workflow ID.
        WorkflowStub workflowById = client.newUntypedWorkflowStub(WORKFLOW_ID);

        // Wait for the workflow to complete and get the result as a String.
        String greeting = workflowById.getResult(String.class);

        // Step 8: Print the result (greeting message) to the console.
        System.out.println(greeting);

        // Step 9: Exit the application after workflow completion.
        System.exit(0);
    }
}

