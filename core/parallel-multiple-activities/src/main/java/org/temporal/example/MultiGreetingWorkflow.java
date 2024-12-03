package org.temporal.example;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

import java.util.List;

// Annotation to define an interface for a Temporal Workflow.
// Workflows orchestrate the execution of activities and handle their results.
@WorkflowInterface
public interface MultiGreetingWorkflow {

    // Annotation to mark this method as the main workflow method.
    // This is the entry point of the workflow.
    @WorkflowMethod
    List<String> getGreetings(List<String> names);
}

