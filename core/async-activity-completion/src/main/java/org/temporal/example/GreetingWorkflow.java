package org.temporal.example;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

// The @WorkflowInterface annotation marks this interface as a Temporal Workflow Interface.
// Workflows orchestrate the execution of activities and define the business logic.
@WorkflowInterface
public interface GreetingWorkflow {

    /**
     * The workflow method that orchestrates the logic to generate a greeting message.
     * <p>
     * This method is invoked by the Temporal client to start the workflow execution.
     *
     * @param name the name of the person to greet (e.g., "John")
     * @return the composed greeting message (e.g., "Hello, John!")
     */
    @WorkflowMethod
    String getGreeting(String name);
}

