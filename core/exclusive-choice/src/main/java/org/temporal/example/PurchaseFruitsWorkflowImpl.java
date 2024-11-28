package org.temporal.example;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;

/**
 * Implementation of the PurchaseFruitsWorkflow interface.
 * This class defines the logic for ordering fruits based on a shopping list.
 */
public class PurchaseFruitsWorkflowImpl implements PurchaseFruitsWorkflow {

    // Create an activity stub to call the OrderFruitsActivities (implemented activities)
    private final OrderFruitsActivities activities = Workflow
            .newActivityStub(OrderFruitsActivities.class, // Activity interface
                    ActivityOptions.newBuilder()
                            .setStartToCloseTimeout(Duration.ofSeconds(2)) // Timeout for activity execution
                            .build()); // Build the activity options

    /**
     * Orders fruits based on the provided shopping list.
     * The method iterates over the list of fruits and calls the corresponding
     * activity methods for each fruit type, appending the results to a StringBuilder.
     *
     * @param list the shopping list containing fruit types and their quantities
     * @return a StringBuilder containing the confirmation of all orders
     */
    @Override
    public StringBuilder orderFruit(ShoppingList list) {
        // Initialize a StringBuilder to collect the results of fruit orders
        StringBuilder shoppingResults = new StringBuilder();

        // Iterate through the shopping list, calling appropriate activity for each fruit
        list.getList()
                .forEach((fruit, amount) -> {
                    // Switch-case to handle different fruit types and order them
                    switch (fruit) {
                        case APPLE:
                            shoppingResults.append(activities.orderApples(amount)); // Order apples
                            break;
                        case BANANA:
                            shoppingResults.append(activities.orderBananas(amount)); // Order bananas
                            break;
                        case CHERRY:
                            shoppingResults.append(activities.orderCherries(amount)); // Order cherries
                            break;
                        case ORANGE:
                            shoppingResults.append(activities.orderOranges(amount)); // Order oranges
                            break;
                        default:
                            shoppingResults.append("Unable to order fruit: ").append(fruit); // Handle unknown fruit types
                            break;
                    }
                });

        // Return the final string builder with all order confirmations
        return shoppingResults;
    }
}
