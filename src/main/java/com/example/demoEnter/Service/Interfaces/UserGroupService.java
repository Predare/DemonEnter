package com.example.demoEnter.Service.Interfaces;


import java.util.List;

import com.example.demoEnter.DTO.UserGroup.*;
import com.example.demoEnter.Exception.ValidationException;

public interface UserGroupService {
    
    UserGroupDto findByName(String name);

    Boolean rolePresence(String groupName,String roleName) throws ValidationException;

    UserGroupDto create(UserGroupDto request) throws ValidationException;

    List<UserGroupDto> delete(String groupName) throws ValidationException;

    UserGroupDto  edit(UserGroupEditDto request) throws ValidationException;

    List<UserGroupDto> findSomeById(List<Integer> iDs);

    List<UserGroupDto> findSomeByNames(List<String> names);

    List<UserGroupDto> delete(Integer groupId) throws ValidationException;
}