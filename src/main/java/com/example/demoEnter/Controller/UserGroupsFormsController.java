package com.example.demoEnter.Controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import com.example.demoEnter.DTO.UserGroup.*;
import com.example.demoEnter.Exception.ValidationException;
import com.example.demoEnter.Security.Role;
import com.example.demoEnter.Service.Interfaces.UserGroupService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@RestController @RequestMapping(path = "groups")
@RequiredArgsConstructor
@Log
@RolesAllowed(Role.GROUP_USERS_ADMIN)
public class UserGroupsFormsController {
    
    private final UserGroupService userGroupService;

    @PostMapping("registry")
    public UserGroupDto register(@RequestBody @Valid UserGroupDto request) throws ValidationException {
        return userGroupService.create(request);
    }

    @DeleteMapping("delete/{name}")
    public List<UserGroupDto> delete(@PathVariable String groupName) throws ValidationException {
        return userGroupService.delete(groupName);
    }

    @DeleteMapping("delete/{id}")
    public List<UserGroupDto> delete(@PathVariable Integer groupId) throws ValidationException {
        return userGroupService.delete(groupId);
    }

    @PostMapping("edit")
    public UserGroupDto edit(@RequestBody @Valid UserGroupEditDto request) throws ValidationException {
        return userGroupService.edit(request);
    }

    @GetMapping("/findSomeById")
    public List<UserGroupDto> findSomeByIds(@RequestParam List<Integer> IDs) {
        log.info("Handling find by ids request: " + IDs);
        return userGroupService.findSomeById(IDs);
    }

    @GetMapping("/findSomeByNames")
    public List<UserGroupDto> findSomeByNames(@RequestParam List<String> names) {
        log.info("Handling find by names request: " + names);
        return userGroupService.findSomeByNames(names);
    }
}
