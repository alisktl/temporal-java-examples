package org.temporal.example;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

/**
 * This interface defines the workflow contract for Temporal.
 * A workflow is a unit of logic that orchestrates activities and other workflows.
 */
@WorkflowInterface
public interface HelloWorkflow {

    /**
     * The @WorkflowMethod annotation marks this method as the entry point of the workflow.
     * This method will be called to start the workflow.
     *
     * @param name The input parameter for the workflow, typically provided when starting the workflow.
     * @return A string result produced by the workflow.
     */
    @WorkflowMethod
    String hello(String name);
}
