package com.WebRise.domain.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "SUBSCRIPTIONS")
public class Subscription {
    @Id
    private String code;
    private String name;
}
