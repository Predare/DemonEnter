package com.example.demoEnter.Entity;

import java.io.Serializable;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "UserGroup")
@Builder
@Data//ломбок аннотация: генерирует геттеры, сеттеры, иквалс, хеш код методы
@AllArgsConstructor(access = AccessLevel.PROTECTED)//ломбок аннотация: конструктор без аргуметов
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserGroup implements Serializable {
    @Id
    private String name;

    @Column
    private String authorities;
}
