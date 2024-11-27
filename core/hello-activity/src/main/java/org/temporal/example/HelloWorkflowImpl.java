package org.temporal.example;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;

/**
 * This class implements the HelloWorkflow interface and provides the workflow logic.
 * Temporal workflows are stateful and deterministic by design.
 * <p>
 * A workflow orchestrates activity executions and defines the overall business logic.
 * It does not execute the activities directly but uses activity stubs to communicate with them.
 */
public class HelloWorkflowImpl implements HelloWorkflow {

    // Define the activity options for configuring activity execution.
    // The start-to-close timeout specifies the maximum time allowed for an activity to complete.
    ActivityOptions options = ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(5)) // Set activity timeout to 5 seconds.
            .build();

    // Create an activity stub to invoke the activities defined in HelloActivities.
    // The stub connects the workflow with the activity worker.
    private final HelloActivities activities = Workflow.newActivityStub(HelloActivities.class, options);

    /**
     * Implements the "hello" workflow method defined in the HelloWorkflow interface.
     * The workflow delegates the task of generating the greeting message to the "hello" activity.
     *
     * @param name The input parameter provided when starting the workflow.
     * @return A greeting message that includes the provided name.
     */
    @Override
    public String hello(String name) {
        // Call the "hello" activity method via the activity stub.
        return activities.hello(name);
    }
}
