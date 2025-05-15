package com.WebRise.service.subscription.mapper;

import com.WebRise.controller.subscription.dto.SubscriptionsCodeResponseDto;
import com.WebRise.controller.subscription.dto.TopSubscriptionsCodeResponseDto;
import com.WebRise.domain.entity.Subscription;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SubscriptionMapper {

    public static SubscriptionsCodeResponseDto toSubscriptionResponseDto(Subscription subscription) {
        return new SubscriptionsCodeResponseDto()
                .setName(subscription.getName())
                .setCode(subscription.getCode());
    }

    public static TopSubscriptionsCodeResponseDto toTopSubscriptionResponseDto(Subscription subscription) {
        return new TopSubscriptionsCodeResponseDto()
                .setName(subscription.getName())
                .setCode(subscription.getCode());
    }

    public static List<TopSubscriptionsCodeResponseDto> toListTopSubscriptionResponseDto(List<Subscription> subscriptions) {
        return subscriptions.stream()
                .map(SubscriptionMapper::toTopSubscriptionResponseDto)
                .toList();
    }

}
