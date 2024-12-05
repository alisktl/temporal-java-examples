package org.temporal.example;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

// Annotation to define an interface as an Activity Interface in Temporal workflow.
// Activities in Temporal are the units of execution that perform specific tasks.
@ActivityInterface
public interface GreetingActivities {

    /**
     * Defines an Activity Method that returns a greeting message.
     *
     * @return a greeting message as a String.
     */
    @ActivityMethod
    String getGreeting();

    /**
     * Defines an Activity Method that composes a personalized greeting
     * using a greeting message and a name.
     *
     * @param greeting the greeting message to use, e.g., "Hello".
     * @param name     the name of the person to include in the greeting.
     * @return a personalized greeting message as a String.
     */
    @ActivityMethod
    String composeGreeting(String greeting, String name);
}
