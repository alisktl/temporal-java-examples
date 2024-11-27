package org.temporal.example;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.client.WorkflowStub;
import io.temporal.serviceclient.WorkflowServiceStubs;

/**
 * This class starts a Temporal workflow execution by interacting with the Temporal service.
 * It serves as the entry point for initiating the "HelloWorkflow".
 */
public class Starter {

    public static void main(String[] args) throws Exception {
        // Step 1: Create a connection to the Temporal service.
        // This connects to a local Temporal server instance using default configurations.
        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();

        // Step 2: Create a WorkflowClient, which is used to start and interact with workflows.
        WorkflowClient client = WorkflowClient.newInstance(service);

        // Step 3: Define workflow options for the workflow execution.
        // - setWorkflowId: Sets a unique ID for the workflow execution. If another workflow with the same ID exists, it will fail unless explicitly allowed.
        // - setTaskQueue: Specifies the task queue that the worker listens to for executing this workflow.
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setWorkflowId("hello-workflow-id") // Unique identifier for the workflow.
                .setTaskQueue(Shared.TASK_QUEUE) // Task queue name, must match the worker's task queue.
                .build();

        // Step 4: Create a type-safe workflow stub for "HelloWorkflow".
        // This stub acts as a local representation of the workflow and is used to start and interact with it.
        HelloWorkflow workflow = client.newWorkflowStub(HelloWorkflow.class, options);

        // Step 5: Start the workflow by calling its "hello" method with the input argument.
        // The workflow executes asynchronously in the Temporal server, and the method returns the result.
        String hello = workflow.hello(args[0]); // Passes the first command-line argument as the input.

        // Step 6: Retrieve the workflow ID from the workflow execution.
        // This ID can be used later to query, signal, or monitor the workflow's progress.
        String workflowId = WorkflowStub.fromTyped(workflow).getExecution().getWorkflowId();

        // Step 7: Print the workflow ID and the result of the workflow execution.
        System.out.println("workflowId: " + workflowId);
        System.out.println(hello);

        // Exit the application after starting the workflow.
        System.exit(0);
    }
}
