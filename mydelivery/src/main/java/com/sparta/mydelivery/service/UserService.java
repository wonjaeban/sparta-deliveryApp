package com.sparta.mydelivery.service;

import com.sparta.mydelivery.dto.LoginRequestDto;
import com.sparta.mydelivery.dto.SignupRequestDto;
import com.sparta.mydelivery.model.User;
import com.sparta.mydelivery.model.UserRoleEnum;
import com.sparta.mydelivery.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String nickname = requestDto.getNickname();
        UserRoleEnum userRole = requestDto.getUserRole();

// 회원 ID 중복 확인
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }

        User user = new User(username, password, nickname, userRole);
        userRepository.save(user);
    }

    public void loginUser(LoginRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        // 회원 ID 중복 확인
        Optional<User> found = userRepository.findByUsername(username);
        if (!found.isPresent()) {
            throw new IllegalArgumentException("존재하지 않는 아이디 입니다.");
        } else if(!passwordEncoder.matches(password, found.get().getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }

    }
}

