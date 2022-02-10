package com.sparta.mydelivery.dto;

import com.sparta.mydelivery.model.UserRoleEnum;
import lombok.Getter;

@Getter
public class SignupRequestDto {
    private UserRoleEnum userRole;
    private String username;
    private String password;
    private String nickname;

}
