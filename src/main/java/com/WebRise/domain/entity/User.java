package com.WebRise.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;


@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String username;
    private String password;
    @ManyToMany
    private List<Subscription> subscriptions;

}
