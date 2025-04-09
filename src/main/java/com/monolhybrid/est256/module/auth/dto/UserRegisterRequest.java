package com.monolhybrid.est256.module.auth.dto;

import com.monolhybrid.est256.module.auth.entity.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserRegisterRequest {

    @NotEmpty(message = "Nama jangan dibiarkan kosong")
    private String name;

    @NotEmpty(message = "Username jangan dibiarkan kosong")
    private String username;

    @NotEmpty(message = "Password jangan dibiarkan kosong")
    private String password;

    private User.RoleBase role;

}
