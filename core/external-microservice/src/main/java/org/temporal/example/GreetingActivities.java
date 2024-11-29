package org.temporal.example;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

/**
 * This interface defines the activities (tasks) that can be executed
 * within a Temporal workflow. Each method represents an individual activity.
 * Temporal activities are units of work that can be executed independently
 * and potentially retried upon failure.
 */
@ActivityInterface // Marks this interface as a Temporal activity interface
public interface GreetingActivities {

    /**
     * This activity method defines a task to greet someone in Spanish.
     *
     * @param name The name of the person to greet.
     * @return A greeting message in Spanish.
     */
    @ActivityMethod
    // Marks this method as a Temporal activity method
    String greetInSpanish(String name);

    /**
     * This activity method defines a task to bid farewell to someone in Spanish.
     *
     * @param name The name of the person to bid farewell to.
     * @return A farewell message in Spanish.
     */
    @ActivityMethod
    // Marks this method as a Temporal activity method
    String farewellInSpanish(String name);
}
