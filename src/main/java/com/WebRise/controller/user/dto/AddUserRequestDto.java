package com.WebRise.controller.user.dto;

import com.WebRise.controller.subscription.dto.AddSubscriptionCodeRequestDto;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class AddUserRequestDto {
    private String username;
    private String password;
    private List<AddSubscriptionCodeRequestDto> subscriptions;
}
