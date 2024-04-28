package com.ziraitakip.backend.service;

import com.ziraitakip.backend.constant.ResponseMessages;
import com.ziraitakip.backend.dto.*;
import com.ziraitakip.backend.model.User;
import com.ziraitakip.backend.repository.UserRepository;
import com.ziraitakip.backend.util.ActivationKeyGenerator;
import com.ziraitakip.backend.util.EmailService;
import com.ziraitakip.backend.util.JwtUtil;
import com.ziraitakip.backend.util.ResponseBody;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private EmailService emailService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    private UserRepository userRepository;
    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User findUserById(Integer id){
        return userRepository.findById(id).orElse(null);
    }

    public ResponseEntity login(LoginReqDto loginReqDto){
        try {
            User user = this.findUserByEmail(loginReqDto.getEmail());
            if(user == null){
                return ResponseBody.sendBody(ResponseMessages.USER_NOT_FOUND(), null, HttpStatus.BAD_REQUEST);
            }
            if(!user.getIs_active()){
                return ResponseBody.sendBody(ResponseMessages.USER_NOT_ACTIVATED(), null, HttpStatus.BAD_REQUEST);
            }
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReqDto.getEmail(), loginReqDto.getPassword()));
            String token = jwtUtil.createToken(user);
            LoginResDto loginRes = new LoginResDto( new LoggedUserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(),user.getRole() == null ? "" : user.getRole().getName()),token);
            return ResponseBody.sendBody(ResponseMessages.LOGIN_SUCCESS(), loginRes, HttpStatus.OK);
        }catch (BadCredentialsException e){
            return ResponseBody.send("Invalid username or password", null, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            System.out.println(e);
            return ResponseBody.send(e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity registerUser(RegisterReqDto registerReqDto){
        try{
            String hashedPassword = passwordEncoder.encode(registerReqDto.getPassword());
            User newUser = new User(registerReqDto.getEmail(), registerReqDto.getFirstName(), registerReqDto.getLastName(), hashedPassword);
            if(newUser.getEmail() == null || newUser.getPassword() == null || newUser.getFirstName() == null || newUser.getLastName() == null){
                return ResponseBody.send(ResponseMessages.USER_REGISTER_DATA_NOT_FOUND(), null, HttpStatus.BAD_REQUEST);
            }
            User user = userRepository.findByEmail(newUser.getEmail());
            if(user != null){
                return ResponseBody.send(ResponseMessages.USER_ALREADY_EXISTS(), null, HttpStatus.BAD_REQUEST);
            }
            // Activation mailinde gozukecek olan activation code'u olusturuyoruz.
            newUser.setActivation_code(ActivationKeyGenerator.generateKey());
            User savedUser = userRepository.save(newUser);
            String token = jwtUtil.createToken(savedUser);
            emailService.sendActivationMail(newUser.getEmail(), newUser, token);
            return ResponseBody.send(ResponseMessages.REGISTER_SUCCESS(), token, HttpStatus.OK);
        }catch (Exception e){
            return ResponseBody.send(e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity confirmUser(String token){
        try {
            UserConfirmDto userConfirmDto = jwtUtil.resolveTokenForActivation(token);
            if(userConfirmDto.getActivation_code() == null || userConfirmDto.getUser_id() == null){
                return ResponseBody.send(ResponseMessages.USER_CONFIRMATION_DATA_NOT_FOUND(), null, HttpStatus.BAD_REQUEST);
            }
            User user = userRepository.findById(userConfirmDto.getUser_id()).orElse(null);
            if(user == null){
                return ResponseBody.send(ResponseMessages.USER_NOT_FOUND(), null, HttpStatus.NOT_FOUND);
            }
            if(user.getIs_active()){
                return ResponseBody.send(ResponseMessages.USER_ALREADY_ACTIVATED(), null, HttpStatus.BAD_REQUEST);
            }
            if(!user.getActivation_code().equals(userConfirmDto.getActivation_code())){
                return ResponseBody.send(ResponseMessages.USER_ACTIVATION_CODE_NOT_MATCH(), null, HttpStatus.BAD_REQUEST);
            }
            user.setIs_active(true);
            user.setActivation_code(null);
            userRepository.save(user);
            return ResponseBody.send(ResponseMessages.USER_ACTIVATED(), null, HttpStatus.OK);
        }catch (Exception e){
            return ResponseBody.send(e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
