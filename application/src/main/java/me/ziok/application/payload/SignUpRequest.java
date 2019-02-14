package me.ziok.application.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SignUpRequest {

    @Size(min = 2, max = 40)
    private String name;

    @Size(max = 15)
    private String nickName;

    @Email
    @Size(max = 40)
    private String email;

    private String password;

}
