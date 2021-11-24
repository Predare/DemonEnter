package com.example.demoEnter.Service.Interfaces;

import java.util.List;

import javax.validation.Valid;

import com.example.demoEnter.DTO.Users.UserPrivateDto;
import com.example.demoEnter.DTO.Users.UserPublicDto;
import com.example.demoEnter.DTO.Users.UserReqistryDto;
import com.example.demoEnter.Exception.ValidationException;

public interface UserService {

    Boolean delete(List<Integer> userId) throws ValidationException;

    List<UserPublicDto> findAll();

    UserPublicDto findById(Integer id);

    List<UserPublicDto> create(List<UserReqistryDto> request) throws ValidationException;

    List<UserPrivateDto> edit(List<UserPrivateDto> request);

    List<UserPrivateDto> get(@Valid List<Integer> request);

    UserPrivateDto findByUsername(@Valid String request);
}
