package org.temporal.example;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;

import java.util.concurrent.CompletableFuture;

// The Starter class is responsible for starting the workflow and initiating the process of interacting with the Temporal service.
public class Starter {

    // Constant to define a unique Workflow ID for this workflow instance.
    public static final String WORKFLOW_ID = "async-activity-completion-task-id";

    public static void main(String[] args) throws Exception {
        // Step 1: Create a WorkflowServiceStubs instance to communicate with the Temporal service.
        // This connects to a local Temporal server for development/testing.
        // For production, configure the connection to point to a remote Temporal service.
        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();

        // Step 2: Create a WorkflowClient instance.
        // The WorkflowClient is the main entry point for interacting with the Temporal service.
        // It facilitates the creation of workflow stubs, the initiation of workflows, and the management of workflow state.
        WorkflowClient client = WorkflowClient.newInstance(service);

        // Step 3: Define workflow options.
        // WorkflowOptions specify the configuration settings for the workflow, such as the task queue
        // and the unique workflow ID. The workflow ID helps identify this specific workflow instance.
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setWorkflowId(WORKFLOW_ID) // Set the unique ID for this workflow.
                .setTaskQueue(Shared.TASK_QUEUE) // Set the task queue to be used for the workflow.
                .build();

        // Step 4: Create a workflow stub for interacting with the workflow implementation.
        // The stub provides methods for invoking the workflow methods remotely.
        GreetingWorkflow workflow = client.newWorkflowStub(GreetingWorkflow.class, options);

        // Step 5: Execute the workflow asynchronously using the execute method.
        // The execute method sends a request to the Temporal service to run the workflow.
        // The result is returned as a CompletableFuture, allowing asynchronous handling of the workflow result.
        CompletableFuture<String> greeting = WorkflowClient.execute(workflow::getGreeting, args[0]);

        // Step 6: Output the result of the workflow execution.
        // The get method blocks and waits for the result of the workflow before printing it to the console.
        System.out.println(greeting.get());

        // Step 7: Exit the program.
        // Once the workflow completes and the result is printed, the program exits.
        System.exit(0);
    }
}
