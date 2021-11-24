package com.example.demoEnter.DTO.UserGroup;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserGroupDto {
    private String name;
    private String authorities;
}
