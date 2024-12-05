package org.temporal.example;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Async;
import io.temporal.workflow.Promise;
import io.temporal.workflow.Workflow;

import java.time.Duration;

// Implementation of the GreetingWorkflow interface, orchestrating activities in the workflow.
public class GreetingWorkflowImpl implements GreetingWorkflow {

    // Stub to interact with activities, specifying activity options such as timeout.
    private final GreetingActivities activities = Workflow.newActivityStub(
            GreetingActivities.class,
            ActivityOptions.newBuilder()
                    .setStartToCloseTimeout(Duration.ofSeconds(10)) // Timeout for each activity call.
                    .build()
    );

    /**
     * Orchestrates the workflow to generate two personalized greeting messages
     * and combines them into a single response.
     *
     * @param name the name of the person to include in the greeting.
     * @return a combined greeting message from two asynchronous activity executions.
     */
    @Override
    public String getGreeting(String name) {

        // Here we invoke our composeGreeting workflow activity two times asynchronously. For this we
        // use {@link io.temporal.workflow.Async} which has support for invoking lambdas. Behind the
        // scenes it allocates a thread to execute each activity method async.
        Promise<String> result1 = Async.function(() -> {
            String greeting = activities.getGreeting(); // Calls the getGreeting activity.
            return activities.composeGreeting(greeting, name); // Calls the composeGreeting activity.
        });

        Promise<String> result2 = Async.function(() -> {
            String greeting = activities.getGreeting(); // Calls the getGreeting activity again.
            return activities.composeGreeting(greeting, name); // Calls the composeGreeting activity again.
        });

        // Waits for both promises to complete and returns the combined result.
        return result1.get() + "\n" + result2.get();
    }
}
