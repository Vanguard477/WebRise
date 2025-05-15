package com.WebRise.controller.subscription.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SubscriptionsCodeResponseDto {
    private String code;
    private String name;
}
