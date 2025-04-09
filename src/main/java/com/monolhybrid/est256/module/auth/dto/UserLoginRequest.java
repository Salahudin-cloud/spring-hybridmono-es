package com.monolhybrid.est256.module.auth.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserLoginRequest {

    @NotEmpty(message = "Username jangan dibiarkan kosong")
    private String username;

    @NotEmpty(message = "Password jangan dibiarkan kosong")
    private String password;

}
