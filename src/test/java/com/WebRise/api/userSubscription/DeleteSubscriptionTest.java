package com.WebRise.api.userSubscription;

import com.WebRise.AbstractAppTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteSubscriptionTest extends AbstractAppTest {

    @Test
    @DisplayName("Удалить единственную подписку пользователя")
    public void deleteSubscription() {
        var user = createAndSaveUser();

        userSubscriptionController.deleteSubscription(user.getId(), "YOUTUBE_PREMIUM");

        var result = userRepository.findUserById(user.getId()).orElseThrow();
        assertEquals(0, result.getSubscriptions().size());
    }

    @Test
    @DisplayName("Ошибка при удалении несуществующей подписки у пользователя")
    public void deleteNonExistingSubscription() {
        var user = createAndSaveUser();

        var throwsNonExistingSubscription = Assertions.assertThrows(RuntimeException.class, () ->
                userSubscriptionController.deleteSubscription(user.getId(), "OKKO"));

        assertEquals("Подписки с кодом: " + "OKKO" + " нет у пользователя с идентификатором: " + user.getId(), throwsNonExistingSubscription.getMessage());
    }


}
