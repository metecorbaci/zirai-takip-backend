package com.ziraitakip.backend.controller;

import com.ziraitakip.backend.dto.LoginReqDto;
import com.ziraitakip.backend.dto.RegisterReqDto;
import com.ziraitakip.backend.service.AuthService;
import com.ziraitakip.backend.util.JwtUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth", description = "User Auth API")
public class AuthController {

    @Autowired
    private AuthService authService;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginReqDto loginReqDto) {
        return authService.login(loginReqDto);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterReqDto registerReqDto){
        return authService.registerUser(registerReqDto);
    }

    @GetMapping("/confirm")
    public ResponseEntity confirm(@RequestParam String token){
        return authService.confirmUser(token);
    }
}
