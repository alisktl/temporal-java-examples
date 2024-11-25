package org.temporal.example;

/**
 * This class implements the HelloWorkflow interface and provides the workflow logic.
 * Temporal workflows are stateful and deterministic by design.
 */
public class HelloWorkflowImpl implements HelloWorkflow {

    /**
     * Implements the "hello" workflow method defined in the HelloWorkflow interface.
     *
     * @param name The input parameter provided when starting the workflow.
     * @return A greeting message that includes the provided name.
     */
    @Override
    public String hello(String name) {
        // The logic for the workflow. This method gets executed when the workflow is started.
        return "Hello, " + name + "!";
    }
}
