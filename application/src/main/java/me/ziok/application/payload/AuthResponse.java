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
    //todo: "Bearer" 가 별 뜻 없으면 딴걸로 바꾸기
    private String tokenType = "Bearer";

}
