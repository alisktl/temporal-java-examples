package org.temporal.example;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

// The @ActivityInterface annotation indicates that this is a Temporal Activity Interface.
// Activities are implementations of business logic that can be executed asynchronously or synchronously.
@ActivityInterface
public interface GreetingActivities {

    /**
     * Composes a greeting message using the provided greeting and name.
     *
     * @param greeting the greeting message (e.g., "Hello")
     * @param name     the name of the person to greet (e.g., "John")
     * @return a composed greeting message combining the greeting and name (e.g., "Hello, John")
     */
    @ActivityMethod
    String composeGreeting(String greeting, String name);
}
