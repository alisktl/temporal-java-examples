package org.temporal.example;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Async;
import io.temporal.workflow.Promise;
import io.temporal.workflow.Workflow;

import java.time.Duration;

// Implementation of the GreetingWorkflow interface.
// The GreetingWorkflowImpl class executes the workflow logic for greeting and farewell messages.
public class GreetingWorkflowImpl implements GreetingWorkflow {

    // Create a new activity stub to call GreetingActivities.
    // The activity options set a timeout for the activity execution.
    private final GreetingActivities activities =
            Workflow.newActivityStub(
                    GreetingActivities.class,
                    ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(10)).build());

    /**
     * This workflow method composes a greeting and a farewell message for the given name.
     * It uses two activities: one for the greeting ("Hello") and one for the farewell ("Bye").
     *
     * @param name A string representing the name to be included in both the greeting and farewell.
     * @return A string containing both the greeting and farewell messages, separated by a new line.
     */
    @Override
    public String getGreeting(String name) {
        // Asynchronously compose a greeting ("Hello") and a farewell ("Bye") using the activities.
        // Each activity is executed concurrently.
        Promise<String> hello = Async.function(activities::composeGreeting, "Hello", name);
        Promise<String> bye = Async.function(activities::composeGreeting, "Bye", name);

        // Wait for both promises to complete and return the combined result.
        return hello.get() + "\n" + bye.get();
    }
}
