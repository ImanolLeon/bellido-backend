package com.example.BelidoMap.services.reglas;

import com.example.BelidoMap.dto.request.LoginRequestDto;
import com.example.BelidoMap.dto.response.AuthResponseDto;

public interface IAuth {
    AuthResponseDto login(LoginRequestDto loginRequestDto);
}