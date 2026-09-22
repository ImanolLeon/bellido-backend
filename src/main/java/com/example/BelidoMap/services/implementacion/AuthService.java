package com.example.BelidoMap.services.implementacion;

import com.example.BelidoMap.dto.request.LoginRequestDto;
import com.example.BelidoMap.dto.response.AuthResponseDto;
import com.example.BelidoMap.security.CustomUserDetails;
import com.example.BelidoMap.security.CustomUserDetailsService;
import com.example.BelidoMap.security.JwtService;
import com.example.BelidoMap.services.reglas.IAuth;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService implements IAuth {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtService jwtService;

    @Override
    public AuthResponseDto login(LoginRequestDto loginRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.dni(), loginRequestDto.password())
        );

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequestDto.dni());
        String token = jwtService.generateToken(userDetails);

        CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;
        return AuthResponseDto.builder()
                .token(token)
                .dni(customUserDetails.getUsuario().getDni())
                .nombre(customUserDetails.getUsuario().getNombre())
                .apellido(customUserDetails.getUsuario().getApellido())
                .admin(customUserDetails.getUsuario().isAdmin())
                .estado("Autenticado correctamente")
                .mensaje("Bienvenido " + customUserDetails.getUsuario().getNombre())
                .build();
    }
}