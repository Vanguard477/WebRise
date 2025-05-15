package com.WebRise.domain.repository;

import com.WebRise.domain.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    boolean existsByUsername(String username);

    @EntityGraph(attributePaths = {"subscriptions"})
    Optional<User> findByUsername(String username);

    @EntityGraph(attributePaths = {"subscriptions"})
    Optional<User> findUserById(String id);


}
