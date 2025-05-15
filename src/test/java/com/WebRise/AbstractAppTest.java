package com.WebRise;

import com.WebRise.controller.subscription.SubscriptionController;
import com.WebRise.controller.user.UserController;
import com.WebRise.controller.userSubscription.UserSubscriptionController;
import com.WebRise.domain.entity.User;
import com.WebRise.domain.repository.SubscriptionRepository;
import com.WebRise.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AbstractAppTest {
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected UserController userController;
    @Autowired
    protected SubscriptionController subscriptionController;
    @Autowired
    protected UserSubscriptionController userSubscriptionController;
    @Autowired
    protected SubscriptionRepository subscriptionRepository;

    protected User createAndSaveUser() {
        var subscription = subscriptionRepository.findByCode("YOUTUBE_PREMIUM").orElseThrow();

        User user = new User()
                .setUsername("username")
                .setPassword("password")
                .setSubscriptions(List.of(subscription));

        return userRepository.save(user);
    }

    @BeforeEach
    public void clearDb() {
        userRepository.deleteAll();
    }
}
