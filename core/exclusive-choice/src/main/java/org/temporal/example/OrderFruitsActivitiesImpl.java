package org.temporal.example;

public class OrderFruitsActivitiesImpl implements OrderFruitsActivities {
    @Override
    public String orderApples(int amount) {
        return "Ordered " + amount + " Apples...";
    }

    @Override
    public String orderBananas(int amount) {
        return "Ordered " + amount + " Bananas...";
    }

    @Override
    public String orderCherries(int amount) {
        return "Ordered " + amount + " Cherries...";
    }

    @Override
    public String orderOranges(int amount) {
        return "Ordered " + amount + " Oranges...";
    }
}
