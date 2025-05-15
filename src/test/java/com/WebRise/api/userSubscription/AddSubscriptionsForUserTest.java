package com.WebRise.api.userSubscription;

import com.WebRise.AbstractAppTest;
import com.WebRise.controller.subscription.dto.AddSubscriptionCodeRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddSubscriptionsForUserTest extends AbstractAppTest {

    @Test
    @DisplayName("Добавление подписки пользователю")
    public void addSubscriptionsForUser() {
        var subscription = subscriptionRepository.findByCode("OKKO").orElseThrow();
        var addSubscriptionCodeRequestDto = new AddSubscriptionCodeRequestDto()
                .setCode(subscription.getCode())
                .setName(subscription.getName());
        var user = createAndSaveUser();

        userSubscriptionController.addSubscriptionsForUser(user.getId(), addSubscriptionCodeRequestDto);

        var result = userRepository.findUserById(user.getId()).orElseThrow();
        assertThat(result.getSubscriptions()).extracting("code").containsExactly("YOUTUBE_PREMIUM", "OKKO");
        assertEquals(2, result.getSubscriptions().size());
    }

    @Test
    @DisplayName("Ошибка при добавление уже существующей подписки у пользователя")
    public void addExistsSubscriptionsForUser() {
        var subscription = subscriptionRepository.findByCode("YOUTUBE_PREMIUM").orElseThrow();
        var addSubscriptionCodeRequestDto = new AddSubscriptionCodeRequestDto()
                .setCode(subscription.getCode())
                .setName(subscription.getName());
        var user = createAndSaveUser();

        var throwsExistsSubscriptions = Assertions.assertThrows(RuntimeException.class, () ->
                userSubscriptionController.addSubscriptionsForUser(user.getId(), addSubscriptionCodeRequestDto));

        assertEquals("Подписка: " + "YouTube Premium" + " уже приобретена", throwsExistsSubscriptions.getMessage());
    }
}
