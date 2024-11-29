package org.temporal.example;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

// Interface defining the activities for the Temporal workflow.
// The @ActivityInterface annotation signifies this as a Temporal activity interface.
@ActivityInterface
public interface GreetingActivities {

    /**
     * Activity method to compose a greeting message.
     *
     * @param greeting A string representing the greeting (e.g., "Hello", "Hi").
     * @param name     A string representing the name to be included in the greeting.
     * @return A string that combines the greeting and name into a full message.
     */
    @ActivityMethod
    String composeGreeting(String greeting, String name);
}
