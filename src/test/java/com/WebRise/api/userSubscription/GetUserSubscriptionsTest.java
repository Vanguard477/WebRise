package com.WebRise.api.userSubscription;

import com.WebRise.AbstractAppTest;
import com.WebRise.domain.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetUserSubscriptionsTest extends AbstractAppTest {

    private User createAndSaveUserWithAllSubscriptions() {
        var subscriptions = subscriptionRepository.findAll();

        User user = new User()
                .setUsername("username")
                .setPassword("password")
                .setSubscriptions(subscriptions);

        userRepository.save(user);
        return user;
    }

    @Test
    @DisplayName("Получить все возможные подписки пользователя")
    public void getUserSubscriptions() {
        var user = createAndSaveUserWithAllSubscriptions();

        var result = userSubscriptionController.getUserSubscriptions(user.getId());

        assertEquals(9, result.size());
    }
}
