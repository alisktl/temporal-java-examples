# Execute Workflow Method Asynchronously and Use Await to Wait for a Condition

This example demonstrates how to use Temporal for workflow orchestration with asynchronous methods and conditional logic. The workflow includes a simple example where a greeting message is generated based on a name provided by the user. Temporal’s `await` statement is used to pause the workflow execution until a signal condition is met.

## Key Features
- **Asynchronous Workflow Execution**: Workflow methods are executed asynchronously, enabling concurrent execution and efficient resource utilization.
- **Condition-Based Workflow Logic**: The workflow uses Temporal's await statement to wait until specific conditions are satisfied before proceeding.
- **Signal-Based Interaction**: External signals can be sent to the workflow to dynamically influence its behavior.

## Prerequisites

Before running the commands, ensure that the following are installed on your system:

- **Java 8 or newer**: The project uses Maven to manage dependencies and build the project.
- **Maven**: Build automation tool used to compile and manage dependencies.
- **Temporal Server**: Temporal's local development server for running workflows.

## Project Overview
### 1. GreetingWorkflow
This is the primary workflow interface, which defines the following methods:

`getGreeting()`: The main entry point of the workflow that generates a greeting message.
`waitForName(String name)`: A signal method to set the name dynamically while the workflow is running.

### 2. GreetingWorkflowImpl
This is the implementation of the workflow logic. Key features include:

Use of Temporal's `await` statement to pause workflow execution until the `name` is provided.
Handling timeouts gracefully by throwing a Temporal-specific `ApplicationFailure` if the condition is not satisfied within a given time frame.

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
