package com.WebRise.controller.user;

import com.WebRise.controller.user.dto.AddUserRequestDto;
import com.WebRise.controller.user.dto.GetUserResponseDto;
import com.WebRise.controller.user.dto.UpdateUserRequestDto;
import com.WebRise.service.user.UserService;
import com.WebRise.service.user.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "Создание пользователя")
    @PostMapping
    public void createUser(@RequestBody AddUserRequestDto addUserRequestDto) {
        userService.createAndSaveUser(addUserRequestDto.getUsername(), addUserRequestDto.getPassword());
    }

    @Operation(summary = "Получение информации о пользователе по идентификатору")
    @GetMapping("/{id}")
    public GetUserResponseDto getUserInfo(@PathVariable String id) {
        return UserMapper.toUserResponseDto(userService.getUserWithSubscriptionById(id));
    }

    @Operation(summary = "Обновить информацию о пользователе по идентификатору")
    @PutMapping("/{id}")
    public void updateUserInfo(@PathVariable String id, @RequestBody UpdateUserRequestDto updateUserRequestDto) {
        userService.updateUser(id, updateUserRequestDto.getUsername(), updateUserRequestDto.getPassword());
    }

    @Operation(summary = "Удалить пользователя")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }


}
