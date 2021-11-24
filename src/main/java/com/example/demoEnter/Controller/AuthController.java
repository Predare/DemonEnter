package com.example.demoEnter.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.demoEnter.DTO.Users.AuthRequestDto;
import com.example.demoEnter.DTO.Users.UserDefaultDetails;
import com.example.demoEnter.DTO.Users.UserPrivateDto;
import com.example.demoEnter.Security.JwtTokenFilter;
import com.example.demoEnter.Security.JwtTokenUtil;
import com.example.demoEnter.Service.Interfaces.UserService;

@RestController @RequestMapping(path = "api/public")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    @PostMapping("perform_login")/*Прайват не нужно пересылать, там пароль и другие данные */
    public ResponseEntity<UserPrivateDto> login(@RequestBody AuthRequestDto request) {
        Logger logger = Logger.getLogger("AuthLogger");
        try {
            logger.log(Level.SEVERE, "Auth try of user: " + request.getUsername());
            Authentication authenticate = authenticationManager
                .authenticate(
                    new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()
                    )
                );

            UserDefaultDetails userDetails = (UserDefaultDetails) authenticate.getPrincipal();
            UserPrivateDto userPrivateDto = userService.findByUsername(userDetails.getUsername());
            logger.log(Level.SEVERE, "User " + request.getUsername() + " succesfully auth-ed");

            ResponseCookie cookie = ResponseCookie
            .from(JwtTokenFilter.COOKIE_NAME, jwtTokenUtil.generateAccessToken(userPrivateDto))
            .maxAge(JwtTokenUtil.TOKEN_AGE).sameSite("Strict")
            .path("/").httpOnly(true).secure(true).build();

            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(userPrivateDto);

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}