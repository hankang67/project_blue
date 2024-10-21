package com.sparta.projectblue.domain.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class SignInDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @NotBlank @Email private String email;
        @NotBlank private String password;
    }

    @Getter
    public static class Response {
        private final String bearerToken;

        public Response(String bearerToken) {
            this.bearerToken = bearerToken;
        }
    }
}
