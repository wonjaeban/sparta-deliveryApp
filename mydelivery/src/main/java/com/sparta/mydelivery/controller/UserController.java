package com.sparta.mydelivery.controller;

import com.sparta.mydelivery.dto.SignupRequestDto;
import com.sparta.mydelivery.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;


    // 회원 가입 요청 처리
    @ResponseBody
    @PostMapping("/user/signup")
    public void registerUser(@RequestBody SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
    }


}
