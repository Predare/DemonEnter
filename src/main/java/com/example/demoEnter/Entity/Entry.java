package com.example.demoEnter.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

import com.example.demoEnter.DTO.Users.UserDefaultDetails;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Entries")
@Builder
@Data//ломбок аннотация: генерирует геттеры, сеттеры, иквалс, хеш код методы
@AllArgsConstructor//ломбок аннотация: конструктор без аргуметов
public class Entry  implements Serializable{

    //Entry DataBase ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //Entry name
    @Column(name = "name")
    private String name;

    //Entry creating date
    @CreatedDate
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    //Entry text
    @Column(name = "text")
    private String text;

    //User id who's created the entry
    @Column(name = "author_details")
    @CreatedBy
    private UserDefaultDetails entryAuthor;
}
