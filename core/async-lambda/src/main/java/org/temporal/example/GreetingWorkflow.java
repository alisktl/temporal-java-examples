package org.temporal.example;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

// Annotation to define an interface as a Workflow Interface in Temporal workflow.
// A workflow in Temporal orchestrates the execution of activities and maintains state.
@WorkflowInterface
public interface GreetingWorkflow {

    /**
     * Defines the Workflow Method that generates a greeting for a given name.
     *
     * @param name the name of the person to include in the greeting.
     * @return a personalized greeting message as a String.
     */
    @WorkflowMethod
    String getGreeting(String name);
}
