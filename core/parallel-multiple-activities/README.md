# Execute multiple Activities in parallel, asynchronously, and wait for them using `Promise.allOf`

This example demonstrates how to use Temporal for workflow orchestration with asynchronous methods and conditional logic. The workflow includes a simple example where a greeting message is generated based on a name provided by the user. Temporal’s `Promise.allOf` is used to wait for multiple activities to complete in parallel, enhancing workflow efficiency.

## Key Features
- **Parallel Activity Execution**: Multiple activities can run simultaneously, making the workflow more efficient.
- **Waiting for All Activities to Complete**: Temporal’s `Promise.allOf` is used to wait for multiple promises (activities) to complete before proceeding.

## Prerequisites

Before running the commands, ensure that the following are installed on your system:

- **Java 8 or newer**: The project uses Maven to manage dependencies and build the project.
- **Maven**: Build automation tool used to compile and manage dependencies.
- **Temporal Server**: Temporal's local development server for running workflows.

## Project Overview
### 1. MultiGreetingWorkflow
This is the primary workflow interface, which defines the following methods:

- `getGreetings(List<String> names)`: The main entry point of the workflow that generates a greeting message for each name provided.

### 2. MultiGreetingWorkflowImpl
This is the implementation of the workflow logic. Key features include:

- Use of Temporal’s `Promise.allOf` to wait for multiple activities to complete in parallel.
- Asynchronous execution of greetings for multiple names, improving workflow throughput.

### 3. GreetingActivities
The activity interface defines the `composeGreeting(String greeting, String name)` method, which generates a greeting message for each name. This activity is called asynchronously for each name in the workflow.

### 4. GreetingActivitiesImpl
The implementation of the activity interface, which constructs a greeting message by concatenating a provided greeting and name.

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

Finally, run the `Starter` class to start the `MultiGreetingWorkflow` and pass a list of names to the workflow. This will trigger the workflow and print the result:

```bash
mvn exec:java \
    -Dexec.mainClass="org.temporal.example.Starter" \
    -Dorg.slf4j.simpleLogger.defaultLogLevel=warn
```

### Output

After running the Starter command, you should see output like the following:

```
Hello John!
Hello Mary!
Hello Michael!
Hello Jennet!
```

### Troubleshooting

- Logs and Debugging: If you encounter issues, consider changing the log level from `warn` to `debug` for more detailed logging:

```bash
-Dorg.slf4j.simpleLogger.defaultLogLevel=debug
```

- **Temporal UI**: If the Temporal UI is not loading on `http://localhost:8080`, ensure that the server started correctly and that the port is not being blocked by any firewall or other services.
