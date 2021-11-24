package com.example.demoEnter.Controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import com.example.demoEnter.DTO.Users.UserPrivateDto;
import com.example.demoEnter.DTO.Users.UserPublicDto;
import com.example.demoEnter.DTO.Users.UserReqistryDto;
import com.example.demoEnter.Exception.ValidationException;
import com.example.demoEnter.Security.Role;
import com.example.demoEnter.Service.Interfaces.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController @RequestMapping(path = "users")
@RequiredArgsConstructor
@RolesAllowed(Role.USER_ADMIN)
public class UsersFormsController {
    
    private final UserService userService;

    @PostMapping("registry")
    public List<UserPublicDto> register(@RequestBody @Valid List<UserReqistryDto> request) throws ValidationException {
        return userService.create(request);
    }

    @DeleteMapping("delete")
    public Boolean delete(@RequestBody @Valid List<Integer> request) throws ValidationException {
        return userService.delete(request);
    }

    @PostMapping("edit")
    public List<UserPrivateDto> edit(@RequestBody @Valid List<UserPrivateDto> request) throws ValidationException {
        return userService.edit(request);
    }

    @PostMapping("get")
    public List<UserPrivateDto> get(@RequestBody @Valid List<Integer> request) throws ValidationException {
        return userService.get(request);
    }


}
