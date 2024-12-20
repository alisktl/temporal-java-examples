package org.temporal.example;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;

import java.util.Arrays;
import java.util.List;

public class Starter {

    // Constant to define a unique Workflow ID for this workflow instance.
    public static final String WORKFLOW_ID = "parallel-multiple-activities-task-id";

    public static void main(String[] args) throws Exception {
        // Step 1: Create a WorkflowServiceStubs instance to communicate with the Temporal service.
        // This connects to a local Temporal server for development/testing.
        // For production, configure the connection to point to a remote Temporal service.
        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();

        // Step 2: Create a WorkflowClient instance.
        // This is the main entry point for interacting with the Temporal service.
        WorkflowClient client = WorkflowClient.newInstance(service);

        // Step 3: Define workflow options.
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setWorkflowId(WORKFLOW_ID)         // Unique ID to identify the workflow instance.
                .setTaskQueue(Shared.TASK_QUEUE)    // Specify the task queue the worker listens to.
                .build();

        // Step 4: Create a stub for the MultiGreetingWorkflow interface.
        // The stub is used to start and interact with the workflow instance.
        MultiGreetingWorkflow workflow = client.newWorkflowStub(MultiGreetingWorkflow.class, options);

        // Step 5: Start the workflow by invoking its method and passing input data.
        // This sends the workflow execution request to the Temporal service.
        List<String> results =
                workflow.getGreetings(Arrays.asList("John", "Mary", "Michael", "Jennet"));

        // Step 6: Print the results of the workflow execution.
        // Each result is a greeting message generated by the workflow.
        for (String result : results) {
            System.out.println(result);
        }

        // Step 7: Exit the application after the workflow is completed.
        System.exit(0);
    }
}
