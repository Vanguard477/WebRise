package com.WebRise.controller.subscription.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AddSubscriptionCodeRequestDto {
    private String code;
    private String name;
}
