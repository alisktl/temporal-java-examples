package org.temporal.example;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface PurchaseFruitsWorkflow {

    @WorkflowMethod
    StringBuilder orderFruit(ShoppingList list);
}
