package com.WebRise.service.subscription;

import com.WebRise.controller.subscription.dto.TopSubscriptionsCodeResponseDto;
import com.WebRise.domain.repository.SubscriptionRepository;
import com.WebRise.service.subscription.mapper.SubscriptionMapper;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
@Accessors(chain = true)
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public List<TopSubscriptionsCodeResponseDto> getTopThreeSubscriptionsResponse() {
        log.info("Начало операции поиска топ подписок");
        var topThreeSubscriptions = subscriptionRepository.findTopThreeSubscriptions();
        log.info("Конец операции поиска топ подписок");
        return SubscriptionMapper.toListTopSubscriptionResponseDto(topThreeSubscriptions);
    }
}
