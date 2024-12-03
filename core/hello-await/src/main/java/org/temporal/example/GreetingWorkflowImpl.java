package org.temporal.example;

import io.temporal.failure.ApplicationFailure;
import io.temporal.workflow.Workflow;

import java.time.Duration;

// Implementation of the GreetingWorkflow interface.
// This class defines the actual behavior of the workflow.
public class GreetingWorkflowImpl implements GreetingWorkflow {

    // A field to hold the name provided via the signal.
    private String name;

    /**
     * The main workflow method that waits for a name signal and returns a greeting.
     * Uses Temporal's Workflow.await() to wait for a signal to set the 'name' field.
     *
     * @return A personalized greeting message.
     */
    @Override
    public String getGreeting() {
        // Workflow.await() pauses the workflow execution until the condition is met or the timeout is reached.
        // - First argument: the timeout duration.
        // - Second argument: a condition (lambda) that must evaluate to true to resume workflow execution.
        boolean ok = Workflow.await(Duration.ofSeconds(10), () -> name != null);

        if (ok) {
            // If the 'name' field is set within 10 seconds, return the greeting message.
            return "Hello, " + name + "!";
        } else {
            // If the signal is not received within 10 seconds, throw an ApplicationFailure to fail the workflow.
            // ApplicationFailure is a Temporal-specific exception used to indicate workflow failure.
            throw ApplicationFailure.newFailure(
                    "WaitForName signal is not received within 10 seconds.", // Error message
                    "signal-timeout" // Type of the failure
            );
        }
    }

    /**
     * Signal method to set the name field.
     * This method is called externally via Temporal signals to pass the name to the workflow.
     *
     * @param name The name to be used in the greeting.
     */
    @Override
    public void waitForName(String name) {
        // Set the received name, which will be used by the getGreeting() method.
        this.name = name;
    }
}

