package me.ziok.application.payload;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class AuthResponse {

    @NonNull
    private String accessToken;
    //todo: "bearer" 가 무슨 뜻이지
    private String tokenType = "Bearer";

}
