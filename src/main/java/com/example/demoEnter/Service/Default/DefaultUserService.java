package com.example.demoEnter.Service.Default;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.example.demoEnter.DTO.Users.UserPrivateDto;
import com.example.demoEnter.DTO.Users.UserPublicDto;
import com.example.demoEnter.DTO.Users.UserReqistryDto;
import com.example.demoEnter.Entity.User;
import com.example.demoEnter.EntityConvertor.UserConvertor;
import com.example.demoEnter.Exception.ValidationException;
import com.example.demoEnter.Repository.UserRepo;
import com.example.demoEnter.Service.Interfaces.UserService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class DefaultUserService implements UserService {

    private final UserRepo userRepo;
    private final UserConvertor userConvertor;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Boolean delete(List<Integer> userIds) throws ValidationException {
        
        if(userIds == null) throw new ValidationException("User id's is null"); 

        for (Integer integer : userIds) {          
            userRepo.deleteById(integer);
        }

        return true;
    }

    @Override
    public List<UserPublicDto> findAll() {
        return userRepo.findAll().stream().map(x -> userConvertor.fromUserToUserPublicDto(x)).collect(Collectors.toList());
    }

    @Override
    public UserPublicDto findById(Integer id) {
        Optional<User> user = userRepo.findById(id);
        if(!user.isPresent()) return null;
        return userConvertor.fromUserToUserPublicDto(user.get());
    }

    @Override
    public UserPrivateDto findByUsername(String name) {
        Optional<User> user = userRepo.findByUsername(name);
        if(!user.isPresent()) return null;
        return userConvertor.fromUserToUserPrivateDto(user.get());
    }

    @Transactional
    public List<UserPublicDto> create(List<UserReqistryDto> request) throws ValidationException {
       
        List<UserPublicDto> responseList = new LinkedList<>();

        for (UserReqistryDto userReqistryDto : request) {
            if (userRepo.findByUsername(userReqistryDto.getUsername()).isPresent()) {
                throw new ValidationException("Username exists!");
            }
            if (!userReqistryDto.getPassword().equals(userReqistryDto.getRepassword())) {
                throw new ValidationException("Passwords don't match!");
            }
            if (userReqistryDto.getUserGroup() == null) {
                throw new ValidationException("User group isn't selected!");
            }
    
            User user = userConvertor.fromUserRegistryDtoToUser(userReqistryDto);
            user.setPassword(passwordEncoder.encode(userReqistryDto.getPassword()));
    
            user = userRepo.save(user);

            responseList.add(userConvertor.fromUserToUserPublicDto(user));
        }

        return responseList;
    }

    @Override
    public List<UserPrivateDto> edit(@Valid List<UserPrivateDto> request) {
        for (UserPrivateDto userPrivateDto : request) {

            if(!userRepo.findById(userPrivateDto.getId()).isPresent()) continue;

            User user = userConvertor.fromUserPrivateDtoToUser(userPrivateDto);

            userRepo.save(user);
        }
        return null;
    }

    @Override
    public List<UserPrivateDto> get(@Valid List<Integer> request) {

        List<UserPrivateDto> foundedUsers = new LinkedList<>(); 

        for (Integer id : request) {

            Optional<User> user = userRepo.findById(id);

            if(!user.isPresent()) continue;

            foundedUsers.add(userConvertor.fromUserToUserPrivateDto(user.get()));
        }
        return foundedUsers;
    }       
}
