package com.example.demoEnter.EntityConvertor;

import com.example.demoEnter.DTO.Users.UserDefaultDetails;
import com.example.demoEnter.DTO.Users.UserPrivateDto;
import com.example.demoEnter.DTO.Users.UserPublicDto;
import com.example.demoEnter.DTO.Users.UserReqistryDto;
import com.example.demoEnter.Entity.User;
import com.example.demoEnter.Service.Interfaces.UserGroupService;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Component
public class UserConvertor {

    private final UserGroupConvertor userGroupConvertor;
    private final UserGroupService userGroupService;

    public UserPublicDto fromUserToUserPublicDto(User user) {
        return UserPublicDto.builder()
            .id(user.getId())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .middleName(user.getMiddleName())
            .build();
    }

    public User fromUserPrivateDtoToUser(UserPrivateDto userPrivateDto) {
        
        return User.builder()
            .id(userPrivateDto.getId())
            .firstName(userPrivateDto.getFirstName())
            .lastName(userPrivateDto.getLastName())
            .middleName(userPrivateDto.getMiddleName())
            .userGroup( userGroupConvertor.fromUserGroupDtoToUserGroup( userGroupService.findByName(userPrivateDto.getUserGroupName()) ))
            .username(userPrivateDto.getUsername())
            .password(userPrivateDto.getPassword())
            .enabled(userPrivateDto.isEnabled())
            .createdAt(userPrivateDto.getCreatedAt())
            .modifiedAt(userPrivateDto.getModifiedAt())
            .build();
    }

    public UserPrivateDto fromUserToUserPrivateDto(User user) {
        
        return UserPrivateDto.builder()
            .id(user.getId())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .middleName(user.getMiddleName())
            .userGroupName(user.getUserGroup().getName())
            .username(user.getUsername())
            .password(user.getPassword())
            .enabled(user.getEnabled())
            .createdAt(user.getCreatedAt())
            .modifiedAt(user.getModifiedAt())
            .build();
    }

    public User fromUserRegistryDtoToUser(UserReqistryDto userReqistryDto)
    {
        return User.builder()
            .firstName(userReqistryDto.getFirstName())
            .lastName(userReqistryDto.getLastName())
            .middleName(userReqistryDto.getMiddleName())
            .userGroup(userGroupConvertor.fromUserGroupDtoToUserGroup(userReqistryDto.getUserGroup()))
            .username(userReqistryDto.getUsername())
            .password(userReqistryDto.getPassword())
            .enabled(userReqistryDto.isEnabled())
            .vkExist(userReqistryDto.getVkExist())
            .whatsAppExist(userReqistryDto.getWhatsAppExist())
            .telegramExist(userReqistryDto.getTelegramExist())
            .viberExist(userReqistryDto.getViberExist())
            .apartment(userReqistryDto.getApartment())
            .bulding(userReqistryDto.getBulding())
            .city(userReqistryDto.getCity())
            .comment(userReqistryDto.getComment())
            .password(userReqistryDto.getPassword())
            .phNumber(userReqistryDto.getPhNumber())
            .email(userReqistryDto.getEmail())
            .street(userReqistryDto.getStreet())
            .build();
    }

    public UserDefaultDetails fromUserToUserDetails(User user) {
        return UserDefaultDetails.builder()
            .id(user.getId())
            .username(user.getUsername())
            .password(user.getPassword())
            .enabled(user.getEnabled())
            .authorities(UserDefaultDetails.convertAuthoritiesFromStringToRole( user.getUserGroup().getAuthorities() ))
            .build();
    }


}
