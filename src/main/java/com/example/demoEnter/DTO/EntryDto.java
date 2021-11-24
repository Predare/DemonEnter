package com.example.demoEnter.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

import com.example.demoEnter.DTO.Users.UserDefaultDetails;

@Builder
@Data
public class EntryDto {

    private Integer id;
    private String name;
    private LocalDateTime createDate;
    private String text;
    private UserDefaultDetails entryAuthor;
}
