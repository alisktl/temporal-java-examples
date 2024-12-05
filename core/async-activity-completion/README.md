# Complete an Activity Execution asynchronously.

This example demonstrates how to use Temporal for workflow orchestration with asynchronous methods, with a key emphasis
on **activity completion**. In this example, a greeting message is generated based on a name provided by the user, and
the **activity completion** feature is used to asynchronously handle the task completion within workflows. This allows
activities to complete their execution outside of the workflow thread, making it more efficient for handling
asynchronous tasks.

## Key Features

- **Asynchronous Activity Completion**: The example highlights how Temporal allows activities to asynchronously complete
  their execution and notify the workflow when done. This improves throughput and efficiency.
- **Workflow and Activity Integration**: The example integrates Temporal workflows and activities, demonstrating how to
  manage tasks asynchronously and handle activity completion outside of the workflow execution.

## Prerequisites

Before running the commands, ensure that the following are installed on your system:

- **Java 8 or newer**: The project uses Maven to manage dependencies and build the project.
- **Maven**: Build automation tool used to compile and manage dependencies.
- **Temporal Server**: Temporal's local development server for running workflows.

## Project Overview

### 1. `GreetingWorkflow`

This is the primary workflow interface that defines the `getGreeting(String name)` method, which generates a greeting
message for the provided name.

### 2. `GreetingWorkflowImpl` Class

This class implements the workflow logic. It uses a `GreetingActivities` stub to asynchronously execute
the `composeGreeting` activity and generate the greeting message.

- The **activity completion** feature is used to notify the workflow when the activity completes its task, allowing the
  workflow to continue executing once the activity is finished.

### 3. `GreetingActivities` Interface

The activity interface defines the `composeGreeting(String greeting, String name)` method, which generates a greeting
message for each name. This activity is called asynchronously for each name in the workflow.

### 4. `GreetingActivitiesImpl` Class

The implementation of the activity interface, which constructs a greeting message by concatenating the provided greeting
and name.

- The activity execution completes asynchronously, and the result is sent back to the workflow via the **activity
  completion** feature, which allows the workflow to continue execution when the activity completes.

## Setup and Running the Example

Follow these steps to set up and run the Temporal workflow example.

### 1. Start Temporal Server in Development Mode

To start the Temporal server locally with a UI at port `8080`, run the following command:

```bash
temporal server start-dev --ui-port 8080
```

This starts a local Temporal server and its associated web UI for monitoring workflows. You can access the UI
at `http://localhost:8080`.

### 2. Build the Project

Use Maven to clean and install the project. This command will remove any previous builds and install the necessary
dependencies:

```bash
mvn clean install
```

### 3. Compile the Project

After installing the dependencies, compile the project using:

```bash
mvn compile
```

### 4. Start the Workflow Worker

Next, run the worker that listens for tasks on the task queue and processes the workflows. This will start
the `GreetingWorker` class:

```bash
mvn exec:java \
    -Dexec.mainClass="org.temporal.example.GreetingWorker" \
    -Dorg.slf4j.simpleLogger.defaultLogLevel=warn
```

This command starts the worker that listens for tasks and executes the workflows.

### 5. Start the Workflow (Starter)

Finally, run the `Starter` class to start the `GreetingWorkflow` and pass a name to the workflow. This will trigger the
workflow and print the result:

```bash
mvn exec:java \
    -Dexec.mainClass="org.temporal.example.Starter" \
    -Dorg.slf4j.simpleLogger.defaultLogLevel=warn \
    -Dexec.args="Alisher"
```

### Output

After running the Starter command, you should see output like the following:

```
Hello, Alisher!
```

### Troubleshooting

- Logs and Debugging: If you encounter issues, consider changing the log level from `warn` to `debug` for more detailed
  logging:

```bash
-Dorg.slf4j.simpleLogger.defaultLogLevel=debug
```

- **Temporal UI**: If the Temporal UI is not loading on `http://localhost:8080`, ensure that the server started
  correctly and that the port is not being blocked by any firewall or other services.
