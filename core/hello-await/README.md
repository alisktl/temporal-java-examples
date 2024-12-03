# Execute Activities Asynchronously with Temporal Workflow

This example demonstrates using Temporal for workflow orchestration with asynchronous methods. The example includes a simple workflow that concurrently generates greeting and farewell messages. By leveraging asynchronous activity execution, the workflow interacts with activities to compose personalized greetings and farewells based on a name provided by the user.

## Prerequisites

Before running the commands, ensure that the following are installed on your system:

- **Java 8 or newer**: The project uses Maven to manage dependencies and build the project.
- **Maven**: Build automation tool used to compile and manage dependencies.
- **Temporal Server**: Temporal's local development server for running workflows.

## Project Overview
### 1. GreetingWorkflow
This is the main workflow that orchestrates the execution of greeting and farewell activities. It calls two activities: one to compose a greeting ("Hello") and one to compose a farewell ("Bye").

### 2. GreetingActivities
This interface defines the activities that the `GreetingWorkflow` relies on. Activities are the actual units of work that run outside the workflow's context. For this example, `GreetingActivities` contains the following method:

- `composeGreeting(String greeting, String name)`: Composes a greeting message based on the provided greeting (e.g., "Hello", "Bye") and the name.

The `GreetingActivitiesImpl` class provides the implementation for this activity.

### 3. Workflow Implementation
The `GreetingWorkflowImpl` class implements the `GreetingWorkflow` interface and orchestrates the greeting and farewell activities. It uses `Async.function` to run both activities concurrently and returns the combined results.

## Setup and Running the Example

Follow these steps to set up and run the Temporal workflow example.

### 1. Start Temporal Server in Development Mode

To start the Temporal server locally with a UI at port `8080`, run the following command:

```bash
temporal server start-dev --ui-port 8080
```

This starts a local Temporal server and its associated web UI for monitoring workflows. You can access the UI at `http://localhost:8080`.

### 2. Build the Project

Use Maven to clean and install the project. This command will remove any previous builds and install the necessary dependencies:

```bash
mvn clean install
```

### 3. Compile the Project

After installing the dependencies, compile the project using:

```bash
mvn compile
```

### 4. Start the Workflow Worker

Next, run the worker that listens for tasks on the task queue and processes the workflows. This will start the `GreetingWorker` class:

```bash
mvn exec:java \
    -Dexec.mainClass="org.temporal.example.GreetingWorker" \
    -Dorg.slf4j.simpleLogger.defaultLogLevel=warn
```

This command starts the worker that listens for tasks and executes the workflows.

### 5. Start the Workflow (Starter)

Finally, run the `Starter` class to start the `GreetingWorkflow` and pass a name to the workflow. This will trigger the workflow and print the result:

```bash
mvn exec:java \
    -Dexec.mainClass="org.temporal.example.Starter" \
    -Dorg.slf4j.simpleLogger.defaultLogLevel=warn \
    -Dexec.args="Alisher"
```

### Output

After running the Starter command, you should see output like the following:

```
Hello Alisher!
Bye Alisher!
```

### Troubleshooting

- Logs and Debugging: If you encounter issues, consider changing the log level from `warn` to `debug` for more detailed logging:

```bash
-Dorg.slf4j.simpleLogger.defaultLogLevel=debug
```

- **Temporal UI**: If the Temporal UI is not loading on `http://localhost:8080`, ensure that the server started correctly and that the port is not being blocked by any firewall or other services.
