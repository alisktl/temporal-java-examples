package org.temporal.example;

/**
 * This class provides the implementation of the HelloActivities interface.
 * It contains the logic for the activities defined in the interface.
 * <p>
 * In Temporal, activity implementations are executed by the activity workers.
 */
public class HelloActivitiesImpl implements HelloActivities {

    /**
     * Implements the "hello" activity method.
     * This method generates a greeting message using the provided name.
     * <p>
     * Temporal will invoke this method when the corresponding activity is called in a workflow.
     *
     * @param name The name of the person to greet.
     * @return A greeting message in the format: "Hello, {name}!"
     */
    @Override
    public String hello(String name) {
        // Returns the greeting message by appending the name to "Hello, ".
        return "Hello, " + name + "!";
    }
}
