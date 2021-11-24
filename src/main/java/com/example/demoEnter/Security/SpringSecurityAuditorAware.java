package com.example.demoEnter.Security;

import java.util.Optional;

import com.example.demoEnter.DTO.Users.UserDefaultDetails;
import com.example.demoEnter.DTO.Users.UserPublicDto;
import com.example.demoEnter.Service.Interfaces.UserService;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class SpringSecurityAuditorAware implements AuditorAware<UserPublicDto> {

    private final UserService userService;

    public Optional<UserPublicDto> getCurrentAuditor() {
  
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
  
      if (authentication == null || !authentication.isAuthenticated()) {
        return null;
      }
  
      return Optional.of( userService.findById(((UserDefaultDetails) authentication.getPrincipal()).getId()) );
    }
  }
