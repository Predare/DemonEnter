
package com.example.demoEnter.DTO.UserGroupDtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserGroupEditDto {
    private String oldName;
    private String oldAuthorities;
    private String newName;
    private String newAuthorities;
}
