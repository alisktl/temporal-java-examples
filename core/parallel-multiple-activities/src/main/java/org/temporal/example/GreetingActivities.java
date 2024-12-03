package org.temporal.example;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

// Annotation to define an interface for Temporal Workflow activities.
// Activities are units of work performed as part of a workflow execution.
@ActivityInterface
public interface GreetingActivities {

    // Annotation to mark this method as an activity method.
    // This method represents a specific activity to be executed within a workflow.
    @ActivityMethod
    String composeGreeting(String greeting, String name);
}

