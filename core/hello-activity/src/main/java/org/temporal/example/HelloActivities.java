package org.temporal.example;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

/**
 * This interface defines the activities that can be executed as part of a Temporal workflow.
 * Activities represent the units of work that run outside the workflow's context.
 * Temporal will use this interface to generate the implementation at runtime.
 */
@ActivityInterface // Marks this interface as a Temporal Activity interface
public interface HelloActivities {

    /**
     * A method that represents an activity to say hello.
     * The @ActivityMethod annotation is optional, but here it explicitly indicates
     * that this method is an activity that Temporal can execute.
     *
     * @param name The name of the person to greet.
     * @return A greeting message.
     */
    @ActivityMethod
    String hello(String name);
}
