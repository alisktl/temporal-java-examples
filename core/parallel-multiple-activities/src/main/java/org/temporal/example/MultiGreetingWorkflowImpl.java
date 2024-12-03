package org.temporal.example;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Async;
import io.temporal.workflow.Promise;
import io.temporal.workflow.Workflow;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MultiGreetingWorkflowImpl implements MultiGreetingWorkflow {

    // Activity stub to invoke methods from the GreetingActivities interface.
    // The activity is configured with options such as a timeout for activity execution.
    private final GreetingActivities activities = Workflow.newActivityStub(
            GreetingActivities.class,
            ActivityOptions.newBuilder()
                    // Sets the maximum time allowed for the activity to complete.
                    .setStartToCloseTimeout(Duration.ofSeconds(2))
                    .build()
    );

    @Override
    public List<String> getGreetings(List<String> names) {
        // List to store the final results (greeting messages).
        List<String> results = new ArrayList<>();

        // List to store Promises representing the asynchronous results of activity calls.
        List<Promise<String>> promiseList = new ArrayList<>();

        // Check if the input list of names is not null.
        if (names != null) {
            // Iterate over the list of names and initiate asynchronous activity calls.
            for (String name : names) {
                // Call the composeGreeting activity asynchronously and add the result to the promise list.
                promiseList.add(Async.function(activities::composeGreeting, "Hello", name));
            }

            // Wait for all asynchronous activity calls to complete.
            Promise.allOf(promiseList).get();

            // Process each promise and extract the results.
            for (Promise<String> promise : promiseList) {
                // Check if the promise was completed successfully (no failure).
                if (promise.getFailure() == null) {
                    // Retrieve the result of the successful promise and add it to the results list.
                    results.add(promise.get());
                }
            }
        }

        // Return the list of greeting messages.
        return results;
    }
}

