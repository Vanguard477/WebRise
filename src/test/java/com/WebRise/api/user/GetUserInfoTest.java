package com.WebRise.api.user;

import com.WebRise.AbstractAppTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetUserInfoTest extends AbstractAppTest {

    @Test
    @DisplayName("Получение информации о пользователе")
    public void getUserInfo() {
        var user = createAndSaveUser();

        var result = userController.getUserInfo(user.getId());

        assertEquals("username", result.getUsername());
        assertEquals("password", result.getPassword());
        assertEquals(1, result.getSubscriptions().size());
    }

    @Test
    @DisplayName("Ошибка при получении информации несуществующего пользователя")
    public void getNonExistingUserInfo() {
        var throwsNonExistingUser = Assertions.assertThrows(RuntimeException.class, () ->
                userController.getUserInfo("1111"));

        assertEquals("Пользователь по идентификатору: " + "1111" + " не найден", throwsNonExistingUser.getMessage());
    }
}
