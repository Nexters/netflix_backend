package me.ziok.application.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.ObjectError;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class GenericResponse {
    private String message;
    private String error;

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(final String error) {
        this.error = error;
    }

}
