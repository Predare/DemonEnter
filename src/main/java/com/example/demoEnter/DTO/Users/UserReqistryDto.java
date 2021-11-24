package com.example.demoEnter.DTO.Users;

import com.example.demoEnter.DTO.UserGroupDtos.UserGroupDto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserReqistryDto {


    private UserGroupDto userGroup;
    private String username;
    private String password;
    private String repassword;   
    @Builder.Default
    private boolean enabled = true;
    private String firstName;
    private String lastName;
    private String middleName;
    private Boolean whatsAppExist;
    private Boolean viberExist;
    private Boolean vkExist;
    private Boolean telegramExist;
    private String phNumber;
    private String email;
    private String city;
    private String street;
    private String bulding;
    private String apartment;
    private String comment;
        
    }
    
