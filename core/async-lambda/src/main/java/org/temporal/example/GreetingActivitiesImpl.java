package org.temporal.example;

// Implementation of the GreetingActivities interface, defining the logic for each activity.
public class GreetingActivitiesImpl implements GreetingActivities {

    /**
     * Returns a simple greeting message.
     *
     * @return a default greeting message ("Hello") as a String.
     */
    @Override
    public String getGreeting() {
        return "Hello";
    }

    /**
     * Composes a personalized greeting using a provided greeting message and a name.
     *
     * @param greeting the greeting message to use, e.g., "Hello".
     * @param name     the name of the person to include in the greeting.
     * @return a personalized greeting message, e.g., "Hello, John!" as a String.
     */
    @Override
    public String composeGreeting(String greeting, String name) {
        return greeting + ", " + name + "!";
    }
}
