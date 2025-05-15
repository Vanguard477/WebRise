package com.WebRise.service.user;

import com.WebRise.controller.subscription.dto.SubscriptionsCodeResponseDto;
import com.WebRise.domain.entity.User;
import com.WebRise.domain.repository.SubscriptionRepository;
import com.WebRise.domain.repository.UserRepository;
import com.WebRise.service.subscription.mapper.SubscriptionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void createAndSaveUser(String username, String password) {
        var exists = userRepository.existsByUsername(username);
        if (exists) {
            throw new RuntimeException("Пользователь с именем: " + username + " уже существует");
        }
        var user = new User()
                .setUsername(username)
                .setPassword(password)
                .setSubscriptions(new ArrayList<>());
        userRepository.save(user);
    }

    public User getUserById(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Пользователь по идентификатору: " + userId + " не найден"));
    }

    public User getUserWithSubscriptionById(String userId) {
        return userRepository.findUserById(userId).orElseThrow(() -> new RuntimeException("Пользователь по идентификатору: " + userId + " не найден"));
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void updateUser(String userId, String username, String password) {
        log.info("Начало выполнения операции: Обновления данных пользователя c идентификатором : {}", userId);
        var user = getUserById(userId)
                .setUsername(username)
                .setPassword(password);
        userRepository.save(user);
        log.info("Конец выполнения операции: Обновления данных о пользователе c идентификатором : {}", userId);
    }

    public void deleteUser(String userId) {
        log.info("Начало выполнения операции: Удаление пользователя c идентификатором : {}", userId);
        userRepository.delete(getUserById(userId));
        log.info("Конец выполнения операции: Удаление пользователя c идентификатором : {}", userId);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void AddUserSubscription(String userId, String name, String code) {
        log.info("Начало выполнения операции: Сохранение подписки {} пользователю c идентификатором : {}", name, userId);
        var user = getUserById(userId);
        var subscription = subscriptionRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Подписки с кодом: " + code + " не найдена"));

        var exists = user.getSubscriptions()
                .contains(subscription);

        if (exists) {
            throw new RuntimeException("Подписка: " + name + " уже приобретена");
        }
        user.getSubscriptions()
                .add(subscription);
        userRepository.save(user);
        log.info("Конец выполнения операции: Сохранение подписки {} пользователю c идентификатором : {}", name, userId);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public List<SubscriptionsCodeResponseDto> getUserSubscriptions(String userId) {
        log.info("Начало выполнения операции: Получения подписок пользователя c идентификатором : {}", userId);
        var user = getUserById(userId);
        log.info("Конец выполнения операции: Получения подписок пользователя c идентификатором : {}", userId);
        return user.getSubscriptions()
                .stream()
                .map(SubscriptionMapper::toSubscriptionResponseDto)
                .toList();
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void deleteUserSubscription(String userId, String subscriptionId) {
        log.info("Начало выполнения операции: Удаление подписки с идентификатором: {}  пользователю c идентификатором : {}", subscriptionId, userId);
        var user = getUserById(userId);
        var subscriptions = user.getSubscriptions()
                .stream()
                .filter(s -> s.getCode()
                        .equals(subscriptionId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Подписки с кодом: " + subscriptionId + " нет у пользователя с идентификатором: " + userId));
        user.getSubscriptions().remove(subscriptions);
        log.info("Конец выполнения операции: Удаление подписки с идентификатором: {}  пользователю c идентификатором : {}", subscriptionId, userId);
    }

}