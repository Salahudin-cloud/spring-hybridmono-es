package com.monolhybrid.est256.module.auth.service;

import com.monolhybrid.est256.common.dto.WebResponse;
import com.monolhybrid.est256.config.JwtProvider;
import com.monolhybrid.est256.module.auth.dto.UserLoginRequest;
import com.monolhybrid.est256.module.auth.dto.UserRegisterRequest;
import com.monolhybrid.est256.module.auth.entity.User;
import com.monolhybrid.est256.module.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
@Transactional
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public WebResponse<Void> registerUser(UserRegisterRequest request) {
        User newUser = User.builder()
                .name(request.getName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        userRepository.save(newUser);

        return WebResponse.<Void>builder()
                .message("Berhasil melakukan register")
                .status(HttpStatus.CREATED.value())
                .build();
    }

    public WebResponse<String>loginUser(UserLoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pengguna tidak ditemukan"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Salah username/password");
        }

        try{
            String token = JwtProvider.generateToken(user.getUsername(), String.valueOf(user.getRole()));

            return WebResponse.<String>builder()
                    .message("Berhasil melakukan login")
                    .data(token)
                    .role(user.getRole())
                    .build();
        } catch (Exception e) {
            return WebResponse.<String>builder()
                    .message(e.getMessage())
                    .build();
        }
    }
}
