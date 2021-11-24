package com.example.demoEnter.EntityConvertor;

import java.util.LinkedList;
import java.util.List;

import com.example.demoEnter.DTO.UserGroupDtos.*;
import com.example.demoEnter.Entity.UserGroup;

import org.springframework.stereotype.Component;

@Component
public class UserGroupConvertor {
    public UserGroupDto fromUserGroupToUserGroupDto(UserGroup userGroup){
        return UserGroupDto.builder()
            .name(userGroup.getName())
            .authorities(userGroup.getAuthorities())
            .build();
    }

    public UserGroup fromUserGroupDtoToUserGroup(UserGroupDto userGroupDto){
        return UserGroup.builder()
            .name(userGroupDto.getName())
            .authorities(userGroupDto.getAuthorities())
            .build();
    }

    public UserGroup fromUserGroupDeleteDtoToUserGroup(UserGroupDeleteDto userGroupDto){
        return UserGroup.builder()
            .name(userGroupDto.getName())
            .build();
    }

    public UserGroup fromUserGroupEditDtoToUserGroup(UserGroupEditDto userGroupDto){
        return UserGroup.builder()
            .name(userGroupDto.getOldName())
            .authorities(userGroupDto.getOldAuthorities())
            .build();
    }

    public List<UserGroupDto> fromUserGroupToUserGroupDto(List<UserGroup> findAll) {
        List<UserGroupDto> groupList = new LinkedList<UserGroupDto>();
        for (UserGroup userGroup: findAll) {
            groupList.add( this.fromUserGroupToUserGroupDto(userGroup) );
        }
        return null;
    }
}
