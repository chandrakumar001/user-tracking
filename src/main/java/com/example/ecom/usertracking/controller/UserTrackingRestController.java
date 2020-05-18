package com.example.ecom.usertracking.controller;

import com.demo.usertracking.api.swagger.model.ResponseMessageDTO;
import com.demo.usertracking.api.swagger.model.UserDTO;
import com.demo.usertracking.api.swagger.model.UserTrackingBareDTO;
import com.demo.usertracking.api.swagger.model.UserTrackingListResponseDTO;
import com.example.ecom.usertracking.validator.UserProperties;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;


@RestController
@Api("User Tracking API")
@AllArgsConstructor(access = PACKAGE, onConstructor = @__(@Autowired))
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class UserTrackingRestController {

    public static final String PATH_USERS = "/users";
    public static final String PATH_USERS_WITH_UID = PATH_USERS + "/{uid}";
    public static final String UID = "uid";


    @GetMapping(path = PATH_USERS)
    public HttpEntity<UserTrackingListResponseDTO> getAllUserTracking() {

        final UserTrackingListResponseDTO usersListResponseDTO = new UserTrackingListResponseDTO();
        return new ResponseEntity<>(usersListResponseDTO, HttpStatus.OK);
    }

    @PostMapping(path = PATH_USERS)
    public HttpEntity<UserTrackingBareDTO> createUserTracking(
            @Valid @RequestBody final UserTrackingBareDTO userBareDTO) {


        System.out.println(userBareDTO);
        return new ResponseEntity<>(userBareDTO, HttpStatus.CREATED);
    }

    @PutMapping(path = PATH_USERS_WITH_UID)
    public HttpEntity<UserTrackingBareDTO> updateUserTracking(
            @PathVariable(UID) final String uid,
            @Valid @RequestBody final UserTrackingBareDTO userBareDTO) {

        System.out.println(uid + "-----" + userBareDTO);
        return new ResponseEntity<>(userBareDTO, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = PATH_USERS_WITH_UID)
    public HttpEntity<UserDTO> getUserTracking(
            @PathVariable(UID) final String uid) {

        System.out.println(uid + "-----");
        final UserDTO userDTO = new UserDTO();
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @DeleteMapping(path = PATH_USERS_WITH_UID)
    public HttpEntity<ResponseMessageDTO> deleteUserTracking(
            @PathVariable(UID) final String uid) {

        System.out.println(uid);
        final ResponseMessageDTO responseMessageDTO = new ResponseMessageDTO();
        return new ResponseEntity<>(responseMessageDTO, HttpStatus.OK);
    }


}