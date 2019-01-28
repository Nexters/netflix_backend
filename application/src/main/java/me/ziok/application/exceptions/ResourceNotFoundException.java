package me.ziok.application.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@AllArgsConstructor
@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

    private String resourceName;
    private String fieldName;
    private Object fieldValue;

//    //todo: allRequiredConstructor로 대체할 수 있는지.
//    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
//        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
//        this.resourceName = resourceName;
//        this.fieldName = fieldName;
//        this.fieldValue= fieldValue;
//    }



}
