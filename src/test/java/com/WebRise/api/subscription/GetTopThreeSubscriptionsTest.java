package com.WebRise.api.subscription;

import com.WebRise.AbstractAppTest;
import com.WebRise.domain.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetTopThreeSubscriptionsTest extends AbstractAppTest {

    private User getUser(String subscriptionCode) {
        var subscriptions = subscriptionRepository.findByCode(subscriptionCode).orElseThrow();
        return new User()
                .setUsername("username" + randomUUID())
                .setPassword("password")
                .setSubscriptions(List.of(subscriptions));
    }

    @Test
    @DisplayName("Получение топ 3 подписки")
    public void getTopThreeSubscriptions() {
        var users = List.of(
                getUser("YOUTUBE_PREMIUM"),
                getUser("YOUTUBE_PREMIUM"),
                getUser("YOUTUBE_PREMIUM"),
                getUser("VK_MUSIC"),
                getUser("VK_MUSIC"),
                getUser("MOVIX"),
                getUser("MOVIX"),
                getUser("NETFLIX"),
                getUser("TWITCH")
        );
        userRepository.saveAll(users);

        var result = subscriptionController.getTopSubscriptions();

        assertThat(result).extracting("code").containsExactly("YOUTUBE_PREMIUM", "MOVIX", "VK_MUSIC");
        assertEquals(3, result.size());
    }
}
