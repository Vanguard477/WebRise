package com.WebRise.controller.user.dto;

import com.WebRise.controller.subscription.dto.SubscriptionsCodeResponseDto;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class GetUserResponseDto {
    private String username;
    private String password;
    private List<SubscriptionsCodeResponseDto> subscriptions;
}
