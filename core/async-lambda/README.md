# Execute part of a Workflow asynchronously in a separate task (thread).

This example demonstrates how to use Temporal's `Async` **API** to orchestrate workflows where parts of the workflow are
executed asynchronously in separate tasks (threads). By leveraging asynchronous execution, workflows can improve
throughput and efficiency by parallelizing task execution.

## Key Features

- **Asynchronous Workflow Execution**: Demonstrates how to use Temporal's `Async.function` to run parts of the workflow
  in separate tasks.
- **Activity Integration**: Highlights how activities can be called asynchronously and their results combined seamlessly
  within the workflow.
- **Efficiency and Parallelism**: Illustrates how asynchronous execution enables workflows to process tasks in parallel,
  reducing overall execution time.

## Prerequisites

Before running the commands, ensure that the following are installed on your system:

- **Java 8 or newer**: The project uses Maven to manage dependencies and build the project.
- **Maven**: Build automation tool used to compile and manage dependencies.
- **Temporal Server**: Temporal's local development server for running workflows.

## Project Overview

### 1. `GreetingWorkflow`

This interface defines the `getGreeting(String name)` method, which is the entry point for the workflow. It generates a
greeting message for a provided name.

### 2. `GreetingWorkflowImpl` Class

This class implements the workflow and showcases the use of `Async.function` to execute tasks in parallel. Specifically:

- Two asynchronous tasks fetch a greeting and compose it with the provided name.
- Both tasks run independently, and their results are joined when needed.

### 3. `GreetingActivities` Interface

The activity interface defines two methods:

- `getGreeting()`: Fetches a generic greeting string.
- `composeGreeting(String greeting, String name)`: Combines the greeting and name into a personalized message.

### 4. `GreetingActivitiesImpl` Class

This is the implementation of the activity interface, providing the logic for fetching and composing greeting messages.

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
