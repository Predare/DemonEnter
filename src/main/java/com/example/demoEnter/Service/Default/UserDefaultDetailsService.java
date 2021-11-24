package com.example.demoEnter.Service.Default;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.demoEnter.Entity.User;
import com.example.demoEnter.EntityConvertor.UserConvertor;
import com.example.demoEnter.Repository.UserRepo;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserDefaultDetailsService implements UserDetailsService {

    private final UserRepo userRepo;
    private final UserConvertor userConvertor;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Logger logger = Logger.getLogger("AuthLogger");
        
        Optional<User> user;
        try{
            user = userRepo.findByUsername(username);
        }catch(Exception e)
        {
            logger.log(Level.SEVERE,  e.getMessage());
            return null;
        }

        return userConvertor.fromUserToUserDetails(user.get());
    }
    
}
