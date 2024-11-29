package org.temporal.example;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;

/**
 * Implementation of the GreetingWorkflow interface.
 * This class defines the workflow logic to orchestrate the execution of activities.
 * The workflow interacts with activities to generate a greeting and a farewell in Spanish.
 */
public class GreetingWorkflowImpl implements GreetingWorkflow {

    // Define activity options with a timeout configuration
    ActivityOptions options = ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(5)) // Maximum time allowed for an activity to complete
            .build();

    /**
     * Create a client stub for the GreetingActivities interface.
     * The stub allows the workflow to call activities while Temporal manages
     * their execution and retries.
     */
    private final GreetingActivities activities = Workflow.newActivityStub(GreetingActivities.class, options);

    /**
     * Workflow implementation for greeting someone in Spanish.
     * Calls the `greetInSpanish` and `farewellInSpanish` activities to generate messages.
     *
     * @param name The name of the person to greet.
     * @return A combined message with the greeting and farewell in Spanish.
     */
    @Override
    public String greetSomeone(String name) {
        // Call the activity to get the greeting message
        String spanishGreeting = activities.greetInSpanish(name);

        // Call the activity to get the farewell message
        String spanishFarewell = activities.farewellInSpanish(name);

        // Return the combined messages
        return "\n" + spanishGreeting + "\n" + spanishFarewell;
    }
}
