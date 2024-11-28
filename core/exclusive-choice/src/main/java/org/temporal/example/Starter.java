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
        // This connects to a local Temporal server instance for testing.
        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();

        // Step 2: Create a WorkflowClient instance to interact with the Temporal service.
        // The WorkflowClient is used to start and interact with workflows.
        WorkflowClient client = WorkflowClient.newInstance(service);

        // Step 3: Create WorkflowOptions to configure the workflow execution.
        // Set the unique workflow ID and task queue for the workflow.
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setWorkflowId("exclusive-choice-task-id") // Unique identifier for the workflow
                .setTaskQueue(Shared.TASK_QUEUE) // Task queue name, must match the worker's task queue
                .build();

        // Step 4: Create a workflow stub using the WorkflowClient.
        // This stub is used to invoke the workflow and trigger its execution.
        PurchaseFruitsWorkflow workflow = client.newWorkflowStub(PurchaseFruitsWorkflow.class, options);

        // Step 5: Create a ShoppingList and add fruit orders to it.
        // The list will contain fruit types and their corresponding quantities.
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.addFruitOrder(Fruits.APPLE, 8);  // Add 8 Apples
        shoppingList.addFruitOrder(Fruits.BANANA, 5); // Add 5 Bananas
        shoppingList.addFruitOrder(Fruits.CHERRY, 1); // Add 1 Cherry
        shoppingList.addFruitOrder(Fruits.ORANGE, 4); // Add 4 Oranges

        // Step 6: Start the workflow by calling the orderFruit method.
        // The workflow will process the shopping list and return the results.
        StringBuilder orderResults = workflow.orderFruit(shoppingList);

        // Step 7: Output the results of the order to the console.
        // The results are accumulated in a StringBuilder and printed.
        System.out.println("Order results: " + orderResults);

        // Step 8: Exit the program.
        System.exit(0);
    }
}
