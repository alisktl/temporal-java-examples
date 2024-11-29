package org.temporal.example;

import io.temporal.activity.Activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Implementation of the GreetingActivities interface.
 * This class provides concrete implementations of the methods to fetch
 * Spanish greetings and farewells from an external service via HTTP requests.
 */
public class GreetingActivitiesImpl implements GreetingActivities {

    /**
     * Retrieves a greeting message in Spanish by calling an external service.
     *
     * @param name The name of the person to greet.
     * @return The greeting message in Spanish.
     */
    @Override
    public String greetInSpanish(String name) {
        return callService("get-spanish-greeting", name);
    }

    /**
     * Retrieves a farewell message in Spanish by calling an external service.
     *
     * @param name The name of the person to bid farewell to.
     * @return The farewell message in Spanish.
     */
    @Override
    public String farewellInSpanish(String name) {
        return callService("get-spanish-farewell", name);
    }

    /**
     * Helper method to call an external HTTP service to fetch the desired response.
     *
     * @param stem The service endpoint path (e.g., "get-spanish-greeting").
     * @param name The name parameter to be sent in the HTTP request.
     * @return The response from the service as a String.
     */
    String callService(String stem, String name) {

        // StringBuilder to store the service response
        StringBuilder builder = new StringBuilder();

        // Base URL template for the service
        String baseUrl = "http://localhost:9999/%s?name=%s";

        // Construct the URL with the given endpoint and encoded name parameter
        URL url = null;
        try {
            url = new URL(String.format(baseUrl, stem, URLEncoder.encode(name, StandardCharsets.UTF_8)));
        } catch (IOException e) {
            // Wrap and rethrow the exception as a Temporal ActivityException
            throw Activity.wrap(e);
        }

        // Read the response from the service
        try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line;
            while ((line = in.readLine()) != null) {
                builder.append(line).append("\n");
            }
        } catch (IOException e) {
            // Wrap and rethrow the exception as a Temporal ActivityException
            throw Activity.wrap(e);
        }

        // Return the full response as a string
        return builder.toString();
    }
}
