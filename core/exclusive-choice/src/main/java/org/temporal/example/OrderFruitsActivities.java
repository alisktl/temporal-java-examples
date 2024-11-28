package org.temporal.example;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

/**
 * This interface defines activities for ordering different types of fruits.
 * It uses Temporal's ActivityInterface and ActivityMethod annotations to
 * define workflows that can interact with these activities.
 */
@ActivityInterface // Marks this interface as a Temporal Activity Interface
public interface OrderFruitsActivities {

    /**
     * Orders a specified amount of apples.
     *
     * @param amount the number of apples to order
     * @return a confirmation message about the order
     */
    @ActivityMethod
    // Indicates this method is a Temporal activity method
    String orderApples(int amount);

    /**
     * Orders a specified amount of bananas.
     *
     * @param amount the number of bananas to order
     * @return a confirmation message about the order
     */
    @ActivityMethod
    // Indicates this method is a Temporal activity method
    String orderBananas(int amount);

    /**
     * Orders a specified amount of cherries.
     *
     * @param amount the number of cherries to order
     * @return a confirmation message about the order
     */
    @ActivityMethod
    // Indicates this method is a Temporal activity method
    String orderCherries(int amount);

    /**
     * Orders a specified amount of oranges.
     *
     * @param amount the number of oranges to order
     * @return a confirmation message about the order
     */
    @ActivityMethod
    // Indicates this method is a Temporal activity method
    String orderOranges(int amount);
}
