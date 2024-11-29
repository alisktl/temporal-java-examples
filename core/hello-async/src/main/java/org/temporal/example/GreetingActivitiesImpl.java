package org.temporal.example;

// Implementation of the GreetingActivities interface.
public class GreetingActivitiesImpl implements GreetingActivities {

    /**
     * Composes a greeting message by concatenating the greeting and name.
     *
     * @param greeting A string representing the greeting (e.g., "Hello", "Hi").
     * @param name     A string representing the name to be included in the greeting.
     * @return A string that combines the greeting and name into a full message, followed by an exclamation mark.
     */
    @Override
    public String composeGreeting(String greeting, String name) {
        // Concatenate greeting and name with a space in between, and add an exclamation mark.
        return greeting + " " + name + "!";
    }
}
