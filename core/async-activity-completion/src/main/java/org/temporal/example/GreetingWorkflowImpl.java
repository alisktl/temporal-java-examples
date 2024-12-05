package org.temporal.example;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;

// Implementation of the GreetingWorkflow interface.
public class GreetingWorkflowImpl implements GreetingWorkflow {

    // Create an activity stub for interacting with the GreetingActivities interface.
    // The stub is used to call activities within the workflow.
    private final GreetingActivities activities = Workflow.newActivityStub(
            GreetingActivities.class,
            // Set the activity timeout options.
            ActivityOptions.newBuilder()
                    .setStartToCloseTimeout(Duration.ofSeconds(20)) // Timeout for the activity (20 seconds).
                    .build());

    /**
     * The workflow method that calls the composeGreeting activity to generate a greeting.
     *
     * @param name the name of the person to greet (e.g., "John")
     * @return the composed greeting message (e.g., "Hello, John!")
     */
    @Override
    public String getGreeting(String name) {
        // Call the composeGreeting activity, passing the greeting and the person's name.
        return activities.composeGreeting("Hello", name);
    }
}

