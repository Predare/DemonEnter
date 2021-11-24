package com.example.demoEnter.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
@Builder
@Data//ломбок аннотация: генерирует геттеры, сеттеры, иквалс, хеш код методы
@AllArgsConstructor//ломбок аннотация: конструктор без аргуметов

public class User implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userGroup")
    private UserGroup userGroup;

    @Column(unique=true, name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @NotNull
    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "middlename")
    private String middleName;
    @Column(name = "whats_app_exist")
    private Boolean whatsAppExist;  
    @Column(name = "viber_exist")
    private Boolean viberExist;
    @Column(name = "vk_exist")
    private Boolean vkExist;
    @Column(name = "telegram_exist")
    private Boolean telegramExist;
    @Column(name = "phone_number")
    private String phNumber;
    @Column(name = "email")
    private String email;
    @Column(name = "city")
    private String city;
    @Column(name = "street")
    private String street;
    @Column(name = "building")
    private String bulding;
    @Column(name = "apartment")
    private String apartment;
    @Column(name = "comment")
    private String comment;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.enabled = true;
    }
}
