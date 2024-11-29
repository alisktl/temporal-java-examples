package org.temporal.example;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

// Interface defining the workflow for composing a greeting message.
// The @WorkflowInterface annotation signifies this as a Temporal workflow interface.
@WorkflowInterface
public interface GreetingWorkflow {

    /**
     * Workflow method to get a greeting message for a specific name.
     *
     * @param name A string representing the name to include in the greeting.
     * @return A string containing the greeting message, typically from the GreetingActivities.
     */
    @WorkflowMethod
    String getGreeting(String name);
}
