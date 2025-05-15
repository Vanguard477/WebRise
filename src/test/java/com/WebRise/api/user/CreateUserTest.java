package com.WebRise.api.user;

import com.WebRise.AbstractAppTest;
import com.WebRise.controller.subscription.dto.AddSubscriptionCodeRequestDto;
import com.WebRise.controller.user.dto.AddUserRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateUserTest extends AbstractAppTest {

    @Test
    @DisplayName("Создание пользователя")
    public void createUser() {
        var addUserRequestDto = new AddUserRequestDto()
                .setUsername("username")
                .setPassword("password")
                .setSubscriptions(List.of(new AddSubscriptionCodeRequestDto()));

        userController.createUser(addUserRequestDto);

        var user = userRepository.findByUsername(addUserRequestDto.getUsername()).orElseThrow();
        assertEquals("username", user.getUsername());
        assertEquals("password", user.getPassword());
    }
}
