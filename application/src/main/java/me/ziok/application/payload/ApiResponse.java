package me.ziok.application.payload;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class ApiResponse {

    @NonNull
    private boolean success;
    @NonNull
    private String message;
}
