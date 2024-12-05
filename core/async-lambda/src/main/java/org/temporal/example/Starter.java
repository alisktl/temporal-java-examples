package org.temporal.example;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;

// The Starter class is responsible for starting the workflow and initiating interaction with the Temporal service.
public class Starter {

    // Constant to define a unique Workflow ID for this workflow instance.
    public static final String WORKFLOW_ID = "async-lambda-task-id";

    public static void main(String[] args) {
        // Ensure that a name argument is provided to personalize the workflow input.
        if (args.length == 0) {
            System.err.println("Please provide a name as an argument.");
            System.exit(1);
        }

        // Step 1: Create a WorkflowServiceStubs instance to communicate with the Temporal service.
        // This connects to a local Temporal server for development or testing purposes.
        // For production, configure the service stubs to connect to a remote Temporal cluster.
        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();

        // Step 2: Create a WorkflowClient instance.
        // The WorkflowClient serves as the primary interface for creating workflow stubs,
        // starting workflows, and managing their state.
        WorkflowClient client = WorkflowClient.newInstance(service);

        // Step 3: Define workflow options.
        // WorkflowOptions configure workflow execution, including the task queue, unique workflow ID,
        // and other properties.
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setWorkflowId(WORKFLOW_ID) // Set the unique ID for this workflow instance.
                .setTaskQueue(Shared.TASK_QUEUE) // Specify the task queue the workflow will use.
                .build();

        // Step 4: Create a workflow stub for the GreetingWorkflow interface.
        // The stub is a proxy that allows interaction with the workflow methods as if they were local.
        GreetingWorkflow workflow = client.newWorkflowStub(GreetingWorkflow.class, options);

        // Step 5: Start the workflow execution by invoking its method.
        // This call will trigger the execution of the workflow logic in the worker.
        String greeting = workflow.getGreeting(args[0]);

        // Step 6: Print the workflow's output to the console.
        System.out.println(greeting);

        // Step 7: Exit the application.
        // The workflow will continue to execute asynchronously in Temporal if needed, even after the application exits.
        System.exit(0);
    }
}
