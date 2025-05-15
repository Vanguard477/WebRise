package com.WebRise.service.user.mapper;

import com.WebRise.controller.user.dto.GetUserResponseDto;
import com.WebRise.domain.entity.User;
import com.WebRise.service.subscription.mapper.SubscriptionMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

    public static GetUserResponseDto toUserResponseDto(User user) {
        var subscriptions = user.getSubscriptions()
                .stream()
                .map(SubscriptionMapper::toSubscriptionResponseDto)
                .toList();
        return new GetUserResponseDto()
                .setUsername(user.getUsername())
                .setPassword(user.getPassword())
                .setSubscriptions(subscriptions);
    }
}
