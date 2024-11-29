# Greeting Workflow with Temporal Using External Microservice

This example demonstrates using Temporal for orchestrating workflows that interact with an **external microservice**. The example includes a simple workflow, `GreetingWorkflow`, that generates personalized greetings and farewells in Spanish by making HTTP requests to an external microservice.

The workflow coordinates activities defined in `GreetingActivities`, such as fetching a Spanish greeting and farewell for a given name. These activities use the `GreetingActivitiesImpl` class to call the external microservice via RESTful APIs to retrieve the appropriate messages.

This project highlights how Temporal can be used to manage workflows that depend on external systems, ensuring reliability, fault tolerance, and scalability, even when interacting with potentially unreliable services. If the external microservice is unavailable or fails, Temporal ensures retries and maintains state until the workflow completes successfully.

## Prerequisites

Before running the commands, ensure that the following are installed on your system:

- **Java 8 or newer**: The project uses Maven to manage dependencies and build the project.
- **Maven**: Build automation tool used to compile and manage dependencies.
- **Temporal Server**: Temporal's local development server for running workflows.

## Project Overview
### 1. GreetingWorkflow
This is the main workflow that orchestrates the execution of greeting and farewell activities. It processes a user's name and delegates tasks to the appropriate activities to fetch personalized greetings and farewells from an external microservice.

### 2. GreetingActivities
This interface defines the activities that the `GreetingWorkflow` relies on. Activities are the actual units of work that run outside the workflow's context. For this example, `GreetingActivities` contains methods to interact with the external microservice:

- `greetInSpanish(String name)`: Fetches a personalized greeting in Spanish for the provided name.
- `farewellInSpanish(String name)`: Fetches a personalized farewell in Spanish for the provided name.

The `GreetingActivitiesImpl` class provides the implementation for these activities.

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
external-microservice-task-id

¡Hola, Alisher!

¡Adiós, Alisher!
```

### Troubleshooting

- Logs and Debugging: If you encounter issues, consider changing the log level from `warn` to `debug` for more detailed logging:

```bash
-Dorg.slf4j.simpleLogger.defaultLogLevel=debug
```

- **Temporal UI**: If the Temporal UI is not loading on `http://localhost:8080`, ensure that the server started correctly and that the port is not being blocked by any firewall or other services.
