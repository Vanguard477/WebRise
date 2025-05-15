package com.WebRise.api.user;

import com.WebRise.AbstractAppTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteUserTest extends AbstractAppTest {

    @Test
    @DisplayName("Удалить существующего пользователя")
    public void deleteUser() {
        var user = createAndSaveUser();

        userController.deleteUser(user.getId());

        var resultAllUsers = userRepository.findAll();
        assertEquals(0, resultAllUsers.size());
    }

    @Test
    @DisplayName("Ошибка при удалении несуществующего пользователя")
    public void deleteNonExistingUser() {
        var throwsNonExistingUser = Assertions.assertThrows(RuntimeException.class, () ->
                userController.deleteUser("1111"));

        assertEquals("Пользователь по идентификатору: " + "1111" + " не найден", throwsNonExistingUser.getMessage());
    }


}
