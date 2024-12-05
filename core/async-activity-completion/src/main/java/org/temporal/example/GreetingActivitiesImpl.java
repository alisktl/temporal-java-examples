package org.temporal.example;

import io.temporal.activity.Activity;
import io.temporal.activity.ActivityExecutionContext;
import io.temporal.client.ActivityCompletionClient;

import java.util.concurrent.ForkJoinPool;

// Implementation of the GreetingActivities interface.
public class GreetingActivitiesImpl implements GreetingActivities {

    // An instance of ActivityCompletionClient to handle asynchronous activity completion.
    private final ActivityCompletionClient completionClient;

    /**
     * Constructor for GreetingActivitiesImpl.
     *
     * @param completionClient the Temporal ActivityCompletionClient used for completing activities asynchronously.
     */
    public GreetingActivitiesImpl(ActivityCompletionClient completionClient) {
        this.completionClient = completionClient;
    }

    /**
     * Composes a greeting message asynchronously using the provided greeting and name.
     *
     * @param greeting the greeting message (e.g., "Hello")
     * @param name     the name of the person to greet (e.g., "John")
     * @return always returns "ignored" as the activity completion is handled asynchronously.
     */
    @Override
    public String composeGreeting(String greeting, String name) {
        // Get the activity execution context.
        ActivityExecutionContext context = Activity.getExecutionContext();

        // Retrieve the unique task token for the activity.
        byte[] taskToken = context.getTaskToken();

        // Asynchronously execute the composeGreetingAsync method.
        // In a real-world scenario, this could involve calling an external service or performing
        // heavy computation.
        ForkJoinPool.commonPool().execute(() -> composeGreetingAsync(taskToken, greeting, name));

        // Indicate that the activity will not be completed when this method returns.
        // Completion will be done explicitly using the ActivityCompletionClient.
        context.doNotCompleteOnReturn();

        // This return value is ignored because completion is handled asynchronously.
        return "ignored";
    }

    /**
     * Asynchronously completes the activity by composing the greeting message.
     *
     * @param taskToken the unique task token identifying the activity execution.
     * @param greeting  the greeting message (e.g., "Hello")
     * @param name      the name of the person to greet (e.g., "John")
     */
    private void composeGreetingAsync(byte[] taskToken, String greeting, String name) {
        // Compose the greeting message.
        String result = greeting + ", " + name + "!";

        // Use the completion client to complete the activity with the composed result.
        completionClient.complete(taskToken, result);
    }
}

