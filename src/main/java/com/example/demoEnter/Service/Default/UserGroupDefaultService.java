package com.example.demoEnter.Service.Default;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.demoEnter.DTO.UserGroupDtos.*;
import com.example.demoEnter.Entity.UserGroup;
import com.example.demoEnter.EntityConvertor.UserGroupConvertor;
import com.example.demoEnter.Exception.ValidationException;
import com.example.demoEnter.Repository.UserGroupRepo;
import com.example.demoEnter.Service.Interfaces.UserGroupService;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserGroupDefaultService implements UserGroupService{
    private UserGroupRepo userGroupRepo;
    private UserGroupConvertor uGroupConvertor;


    

    @Override
    public Boolean rolePresence(String groupName,String roleName) throws ValidationException {
        Optional<UserGroup> userGroup = userGroupRepo.findByName(groupName);
        if(!userGroup.isPresent()) throw new ValidationException("User group isn't exist!");

        UserGroupDto userGroupDto = uGroupConvertor.fromUserGroupToUserGroupDto(userGroup.get());
        
        if(userGroupDto.getAuthorities().contains(roleName))return true;
        else return false;
    }




    @Override
    public UserGroupDto findByName(String name) {
        Optional<UserGroup> userGroup = userGroupRepo.findByName(name);
        if(!userGroup.isPresent()) return null;
        
        UserGroupDto userGroupDto = uGroupConvertor.fromUserGroupToUserGroupDto(userGroup.get());
        return userGroupDto;
    }

    @Transactional
    @Override
    public UserGroupDto create(UserGroupDto request) throws ValidationException {
        if (userGroupRepo.findByName(request.getName()).isPresent()) {
            throw new ValidationException("Username exists!");
        }
        if (request.getAuthorities() == null) {
            throw new ValidationException("User group authorities is null!");
        }

        UserGroup userGroup = uGroupConvertor.fromUserGroupDtoToUserGroup(request);

        userGroupRepo.save(userGroup);
        return uGroupConvertor.fromUserGroupToUserGroupDto(userGroup);
    }




    @Override
    public List<UserGroupDto> delete(String groupName) throws ValidationException {
        Optional<UserGroup> userGroup = userGroupRepo.findByName(groupName);
        
        if (!userGroup.isPresent()) {
            throw new ValidationException("Username not exists!");
        }
        userGroupRepo.delete(userGroup.get());

        return uGroupConvertor.fromUserGroupToUserGroupDto(userGroupRepo.findAll());
    }

    @Override
    public List<UserGroupDto> delete(Integer groupId) throws ValidationException {

        Optional<UserGroup> userGroup = userGroupRepo.findById(groupId);
        
        if (!userGroup.isPresent()) {
            throw new ValidationException("Username not exists!");
        }
        userGroupRepo.delete(userGroup.get());

        return uGroupConvertor.fromUserGroupToUserGroupDto(userGroupRepo.findAll());
    }


    @Override
    public UserGroupDto edit(UserGroupEditDto request) throws ValidationException {
        if (!userGroupRepo.findByName(request.getOldName()).isPresent()) {
            throw new ValidationException("Username not exists!");
        }

        UserGroup userGroup = uGroupConvertor.fromUserGroupEditDtoToUserGroup(request);

        if(request.getNewName() != "")userGroup.setName(request.getNewName());
        if(request.getNewAuthorities() != "")userGroup.setAuthorities(request.getNewAuthorities());

        userGroupRepo.save(userGroup);
        return uGroupConvertor.fromUserGroupToUserGroupDto(userGroup);
    }




    @Override
    public List<UserGroupDto> findSomeById(List<Integer> iDs) {
        List<UserGroupDto> userGroups = new LinkedList<>();
        
        for (Integer id : iDs) {
            Optional<UserGroup> userGroup = userGroupRepo.findById(id);
            if(!userGroup.isPresent()) continue;

            userGroups.add( uGroupConvertor.fromUserGroupToUserGroupDto(userGroup.get()) );
        }
        return userGroups;
    }




    @Override
    public List<UserGroupDto> findSomeByNames(List<String> names) {
        List<UserGroupDto> userGroups = new LinkedList<>();
        
        for (String name : names) {
            Optional<UserGroup> userGroup = userGroupRepo.findByName(name);
            if(!userGroup.isPresent()) continue;

            userGroups.add( uGroupConvertor.fromUserGroupToUserGroupDto(userGroup.get()) );
        }
        return userGroups;
    }
    
}
