package org.temporal.example;

// Implementation class for the GreetingActivities interface.
// This class contains the actual logic for the activity methods defined in the interface.
public class GreetingActivitiesImpl implements GreetingActivities {

    // Overrides the composeGreeting method defined in the GreetingActivities interface.
    // This method provides the implementation for composing a greeting message.
    @Override
    public String composeGreeting(String greeting, String name) {
        // Concatenates the greeting, a space, the name, and an exclamation mark.
        // Example: If greeting = "Hello" and name = "John", the result will be "Hello John!".
        return greeting + " " + name + "!";
    }
}

