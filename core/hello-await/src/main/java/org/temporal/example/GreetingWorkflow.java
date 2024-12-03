package org.temporal.example;

import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

// The @WorkflowInterface annotation marks this interface as a Temporal workflow definition.
// Temporal workflows represent long-running and durable processes.
@WorkflowInterface
public interface GreetingWorkflow {

    /**
     * The @WorkflowMethod annotation identifies the main entry point of the workflow.
     * This method is executed when the workflow is started.
     *
     * @return A greeting message as a String.
     */
    @WorkflowMethod
    String getGreeting();

    /**
     * The @SignalMethod annotation marks this method as a signal handler.
     * Signal methods are used to send external input to a running workflow.
     *
     * @param name The name to be used in the greeting, sent as a signal to the workflow.
     */
    @SignalMethod
    void waitForName(String name);
}
