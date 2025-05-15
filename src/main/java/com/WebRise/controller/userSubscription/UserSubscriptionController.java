package com.WebRise.controller.userSubscription;

import com.WebRise.controller.subscription.dto.AddSubscriptionCodeRequestDto;
import com.WebRise.controller.subscription.dto.SubscriptionsCodeResponseDto;
import com.WebRise.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/users/{id}/subscriptions")
@RequiredArgsConstructor
public class UserSubscriptionController {
    private final UserService userService;

    @Operation(summary = "Добавление подписки пользователю")
    @PostMapping
    public void addSubscriptionsForUser(@PathVariable String id, @RequestBody AddSubscriptionCodeRequestDto addSubscriptionCodeRequestDto) {
        userService.AddUserSubscription(id, addSubscriptionCodeRequestDto.getName(), addSubscriptionCodeRequestDto.getCode());
    }

    @Operation(summary = "Получить подписки пользователя")
    @GetMapping
    public List<SubscriptionsCodeResponseDto> getUserSubscriptions(@PathVariable String id) {
        return userService.getUserSubscriptions(id);
    }

    @Operation(summary = "Удалить подписку пользователя")
    @DeleteMapping("/{sub_id}")
    public void deleteSubscription(@PathVariable String id, @PathVariable String subscriptionId) {
        userService.deleteUserSubscription(id, subscriptionId);
    }
}
