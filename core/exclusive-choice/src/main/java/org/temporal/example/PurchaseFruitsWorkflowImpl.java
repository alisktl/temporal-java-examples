package org.temporal.example;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;

public class PurchaseFruitsWorkflowImpl implements PurchaseFruitsWorkflow {

    private final OrderFruitsActivities activities = Workflow
            .newActivityStub(OrderFruitsActivities.class,
                    ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(2)).build());

    @Override
    public StringBuilder orderFruit(ShoppingList list) {
        StringBuilder shoppingResults = new StringBuilder();

        list.getList()
                .forEach((fruit, amount) -> {
                    switch(fruit) {
                        case APPLE:
                            shoppingResults.append(activities.orderApples(amount));
                            break;
                        case BANANA:
                            shoppingResults.append(activities.orderBananas(amount));
                            break;
                        case CHERRY:
                            shoppingResults.append(activities.orderCherries(amount));
                            break;
                        case ORANGE:
                            shoppingResults.append(activities.orderOranges(amount));
                            break;
                        default:
                            shoppingResults.append("Unable to order fruit: ").append(fruit);
                            break;
                    }
                });

        return shoppingResults;
    }
}
