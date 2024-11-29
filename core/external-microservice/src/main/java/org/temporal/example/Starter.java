package org.temporal.example;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.client.WorkflowStub;
import io.temporal.serviceclient.WorkflowServiceStubs;

/**
 * The Starter class is responsible for starting a Temporal workflow.
 * It connects to the Temporal server, creates a workflow stub, and starts the workflow execution.
 */
public class Starter {

    public static void main(String[] args) throws Exception {

        // Step 1: Create a WorkflowServiceStubs instance to communicate with the Temporal service.
        // This establishes a gRPC connection to the Temporal server.
        // The 'newLocalServiceStubs' method connects to a local Temporal server instance for testing.
        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();

        // Step 2: Create a WorkflowClient to interact with the Temporal server.
        // This client is used to create workflow stubs and start workflow executions.
        WorkflowClient client = WorkflowClient.newInstance(service);

        // Step 3: Define workflow options.
        // Workflow options specify configuration details such as workflow ID and task queue.
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setWorkflowId("external-microservice-task-id") // Unique ID for the workflow execution
                .setTaskQueue(Shared.TASK_QUEUE)               // Name of the task queue for worker polling
                .build();

        // Step 4: Create a typed workflow stub for the GreetingWorkflow interface.
        // The stub allows you to call workflow methods as if the workflow were a local object.
        GreetingWorkflow workflow = client.newWorkflowStub(GreetingWorkflow.class, options);

        // Step 5: Start the workflow execution by calling its method.
        // Pass the first command-line argument (args[0]) as the name parameter.
        String greeting = workflow.greetSomeone(args[0]);

        // Step 6: Retrieve the workflow ID for tracking and logging purposes.
        // This ID can be used to monitor or query the workflow execution in Temporal.
        String workflowId = WorkflowStub.fromTyped(workflow).getExecution().getWorkflowId();

        // Step 7: Print the workflow ID and the returned greeting message to the console.
        System.out.println(workflowId + "\n" + greeting);

        // Step 8: Exit the application after the workflow has completed execution.
        System.exit(0);
    }
}
