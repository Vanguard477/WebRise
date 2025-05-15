package com.WebRise.domain.repository;

import com.WebRise.domain.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, String> {

    @Query(value =
            "SELECT subscriptions.*"
            + " FROM subscriptions"
            + " JOIN users_subscriptions ON subscriptions.code = users_subscriptions.subscriptions_code"
            + " GROUP BY subscriptions.code"
            + " ORDER BY COUNT(users_subscriptions.user_id) DESC "
            + " LIMIT 3",
            nativeQuery = true)
    List<Subscription> findTopThreeSubscriptions();

    Optional<Subscription> findByCode(String code);
}
