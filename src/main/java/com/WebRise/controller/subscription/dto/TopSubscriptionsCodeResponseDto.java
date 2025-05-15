package com.WebRise.controller.subscription.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TopSubscriptionsCodeResponseDto {
    private String code;
    private String name;
}
