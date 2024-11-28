package org.temporal.example;

import java.util.HashMap;
import java.util.Map;

/**
 * This class manages a shopping list for ordering fruits.
 * It stores the fruit types and their respective quantities.
 */
public class ShoppingList {

    // A map to store the fruit types (Fruits enum) and their quantities (Integer)
    private Map<Fruits, Integer> list = new HashMap<>();

    /**
     * Adds a fruit order to the shopping list.
     * If the fruit already exists, its quantity will be updated.
     *
     * @param fruit  the type of fruit to add
     * @param amount the quantity of the fruit to add
     */
    public void addFruitOrder(Fruits fruit, int amount) {
        // Add or update the fruit's order in the list
        list.put(fruit, amount);
    }

    /**
     * Retrieves the entire shopping list with fruits and their quantities.
     *
     * @return the shopping list map containing fruit types and quantities
     */
    public Map<Fruits, Integer> getList() {
        // Return the current shopping list
        return list;
    }
}
