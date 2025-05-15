package com.WebRise.api.user;

import com.WebRise.AbstractAppTest;
import com.WebRise.controller.user.dto.UpdateUserRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateUserInfoTest extends AbstractAppTest {

    private UpdateUserRequestDto createUpdateUserRequestDto() {
        return new UpdateUserRequestDto()
                .setUsername("newUsername")
                .setPassword("newPassword");
    }

    @Test
    @DisplayName("Обновить имя пользователя и пароль в информации о пользователе")
    public void updateUserInfo() {
        var user = createAndSaveUser();
        var updateUserRequestDto = createUpdateUserRequestDto();

        userController.updateUserInfo(user.getId(), updateUserRequestDto);

        var resultUpdatedUser = userRepository.findUserById(user.getId()).orElseThrow();
        assertEquals("newUsername", resultUpdatedUser.getUsername());
        assertEquals("newPassword", resultUpdatedUser.getPassword());
    }

    @Test
    @DisplayName("Ошибка при обновлении информации несуществующего пользователя")
    public void updateInfoForNonExistingUser() {
        var updateUserRequestDto = createUpdateUserRequestDto();

        var throwsNonExistingUser = Assertions.assertThrows(RuntimeException.class, () ->
                userController.updateUserInfo("1111", updateUserRequestDto));

        assertEquals("Пользователь по идентификатору: " + "1111" + " не найден", throwsNonExistingUser.getMessage());
    }
}
