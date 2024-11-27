# Temporal Workflow Basic Example

This repository demonstrates a basic example of using Temporal for workflow orchestration. The example includes a simple `HelloWorkflow` that greets a user by their name.

## Prerequisites

Before running the commands, ensure that the following are installed on your system:

- **Java 8 or newer**: The project uses Maven to manage dependencies and build the project.
- **Maven**: Build automation tool used to compile and manage dependencies.
- **Temporal Server**: Temporal's local development server for running workflows.

## Project Overview
### 1. HelloWorkflow
This is the main workflow that defines and executes the greeting logic. It takes a user's name as input and directly generates a greeting message.

The workflow implementation (`HelloWorkflowImpl`) defines the logic for the `hello` method. Since this example does not use activities, all the logic for generating the greeting is implemented directly in the workflow class.

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

Next, run the worker that listens for tasks on the task queue and processes the workflows. This will start the `HelloWorkflowWorker` class:

```bash
mvn exec:java \
    -Dexec.mainClass="org.temporal.example.HelloWorkflowWorker" \
    -Dorg.slf4j.simpleLogger.defaultLogLevel=warn
```

This command starts the worker that listens for tasks and executes the workflows.

### 5. Start the Workflow (Starter)

Finally, run the `Starter` class to start the `HelloWorkflow` and pass the user's name (in this case, "Alisher") as an argument. This will trigger the workflow and print the result:

```bash
mvn exec:java \
    -Dexec.mainClass="org.temporal.example.Starter" \
    -Dorg.slf4j.simpleLogger.defaultLogLevel=warn \
    -Dexec.args="Alisher"
```

### Output

After running the Starter command, you should see output like the following:

```
workflowId: hello-workflow-id
Hello, Alisher!
```

### Troubleshooting

- Logs and Debugging: If you encounter issues, consider changing the log level from `warn` to `debug` for more detailed logging:

```bash
-Dorg.slf4j.simpleLogger.defaultLogLevel=debug
```

- **Temporal UI**: If the Temporal UI is not loading on `http://localhost:8080`, ensure that the server started correctly and that the port is not being blocked by any firewall or other services.
