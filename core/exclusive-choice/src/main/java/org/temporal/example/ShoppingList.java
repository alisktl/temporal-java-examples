package org.temporal.example;

import java.util.HashMap;
import java.util.Map;

public class ShoppingList {

    private Map<Fruits, Integer> list = new HashMap<>();

    public void addFruitOrder(Fruits fruit, int amount) {
        list.put(fruit, amount);
    }

    public Map<Fruits, Integer> getList() {
        return list;
    }
}
