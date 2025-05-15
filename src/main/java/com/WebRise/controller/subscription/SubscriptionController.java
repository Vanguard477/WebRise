package com.WebRise.controller.subscription;

import com.WebRise.controller.subscription.dto.TopSubscriptionsCodeResponseDto;
import com.WebRise.service.subscription.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @Operation(summary = "Получить ТОП-3 популярных подписок")
    @GetMapping("/top")
    public List<TopSubscriptionsCodeResponseDto> getTopSubscriptions() {
        return subscriptionService.getTopThreeSubscriptionsResponse();
    }

}
