package org.temporal.example;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface OrderFruitsActivities {

    @ActivityMethod
    String orderApples(int amount);

    @ActivityMethod
    String orderBananas(int amount);

    @ActivityMethod
    String orderCherries(int amount);

    @ActivityMethod
    String orderOranges(int amount);
}
