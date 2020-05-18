package com.example.ecom.usertracking;

import com.example.ecom.usertracking.validator.UserProperties;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@RestController
@Api("Load Properties API")
@AllArgsConstructor(access = PACKAGE, onConstructor = @__(@Autowired))
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class LoadPropertiesRestController {

    @NonNull UserProperties userProperties;

    @GetMapping(path = "")
    public HttpEntity<String> getAllLoadProperties() {

        System.out.println(userProperties);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
