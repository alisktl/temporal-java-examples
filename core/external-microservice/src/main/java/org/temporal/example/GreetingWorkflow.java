package org.temporal.example;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

/**
 * This interface defines a Temporal Workflow for generating greetings.
 * A workflow is a business process that orchestrates the execution of activities
 * and handles retries, timeouts, and state management.
 */
@WorkflowInterface // Marks this interface as a Temporal workflow interface
public interface GreetingWorkflow {

    /**
     * The main entry point for the workflow.
     * This method defines the workflow logic for greeting someone.
     *
     * @param name The name of the person to greet.
     * @return A greeting message.
     */
    @WorkflowMethod
    // Marks this method as the entry point for the workflow
    String greetSomeone(String name);

}
