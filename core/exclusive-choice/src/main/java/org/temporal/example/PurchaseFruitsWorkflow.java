package org.temporal.example;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

/**
 * This interface defines the workflow for purchasing fruits.
 * It utilizes Temporal's WorkflowInterface and WorkflowMethod annotations
 * to define a workflow that interacts with the provided fruit orders.
 */
@WorkflowInterface // Marks this interface as a Temporal Workflow Interface
public interface PurchaseFruitsWorkflow {

    /**
     * The main method for ordering fruits in the workflow.
     * Takes a shopping list containing various fruits and their quantities,
     * and processes the order.
     *
     * @param list the shopping list containing fruit types and quantities
     * @return a StringBuilder containing the confirmation of the order
     */
    @WorkflowMethod
    // Marks this method as a Temporal Workflow Method
    StringBuilder orderFruit(ShoppingList list);
}
