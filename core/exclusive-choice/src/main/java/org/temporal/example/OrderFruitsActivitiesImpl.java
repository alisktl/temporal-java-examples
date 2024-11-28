package org.temporal.example;

/**
 * Implementation of the OrderFruitsActivities interface.
 * This class provides the actual logic for ordering different fruits.
 */
public class OrderFruitsActivitiesImpl implements OrderFruitsActivities {

    /**
     * Orders a specified amount of apples and returns a confirmation message.
     *
     * @param amount the number of apples to order
     * @return a confirmation message
     */
    @Override
    public String orderApples(int amount) {
        // Return a string confirming the apple order
        return "Ordered " + amount + " Apples...";
    }

    /**
     * Orders a specified amount of bananas and returns a confirmation message.
     *
     * @param amount the number of bananas to order
     * @return a confirmation message
     */
    @Override
    public String orderBananas(int amount) {
        // Return a string confirming the banana order
        return "Ordered " + amount + " Bananas...";
    }

    /**
     * Orders a specified amount of cherries and returns a confirmation message.
     *
     * @param amount the number of cherries to order
     * @return a confirmation message
     */
    @Override
    public String orderCherries(int amount) {
        // Return a string confirming the cherry order
        return "Ordered " + amount + " Cherries...";
    }

    /**
     * Orders a specified amount of oranges and returns a confirmation message.
     *
     * @param amount the number of oranges to order
     * @return a confirmation message
     */
    @Override
    public String orderOranges(int amount) {
        // Return a string confirming the orange order
        return "Ordered " + amount + " Oranges...";
    }
}
