package com.example.demoEnter;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

import com.example.demoEnter.DTO.Users.UserReqistryDto;
import com.example.demoEnter.Exception.ValidationException;
import com.example.demoEnter.Service.Interfaces.UserGroupService;
import com.example.demoEnter.Service.Interfaces.UserService;


@Data
@AllArgsConstructor
@Component
public class DatabaseInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private final UserGroupService userGroupService;

    private final List<String> usernames = List.of(
            "supera"    
    );

    private final String password = "supera";

    private final UserService userService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

        List<UserReqistryDto> newUsers = new LinkedList<>();

        for (int i = 0; i < usernames.size(); ++i) {
            UserReqistryDto request = 
                UserReqistryDto.builder()
            .username(usernames.get(i))
            .password(password)
            .repassword(password)
            .userGroup(userGroupService.findByName("super_admin"))
            .build();

            newUsers.add(request);
            
        }
        try {
            userService.create(newUsers);
        } catch (ValidationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}