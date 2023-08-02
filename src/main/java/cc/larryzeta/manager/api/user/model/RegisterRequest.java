package cc.larryzeta.manager.api.user.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RegisterRequest {

    @NotBlank
    String username;

    @NotBlank
    String email;

    @NotBlank
    String password;

}
