package com.example.ecom.usertracking.resource;

import com.demo.usertracking.api.swagger.model.ResponseMessageDTO;
import com.demo.usertracking.api.swagger.model.UserTrackingBareDTO;
import com.demo.usertracking.api.swagger.model.UserTrackingDTO;
import com.example.ecom.usertracking.service.UserTrackingCommandService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;


@RestController
@Api("User Tracking API")
@AllArgsConstructor(access = PACKAGE, onConstructor = @__(@Autowired))
@FieldDefaults(level = PRIVATE, makeFinal = true)
@ConditionalOnProperty(name = "app.write.enabled", havingValue = "false")
public class UserTrackingCommandRestController {

    public static final String PATH_USERS = "/users";
    public static final String PATH_USERS_WITH_UID = PATH_USERS + "/{uid}";
    public static final String UID = "uid";

    @NonNull UserTrackingCommandService userTrackingCommandService;

    @PostMapping(path = PATH_USERS)
    public HttpEntity<UserTrackingDTO> createUserTracking(
            @Valid @RequestBody final UserTrackingBareDTO userTrackingBareDTO) {

        final UserTrackingDTO userTrackingDTO = userTrackingCommandService.createUserTracking(
                userTrackingBareDTO
        );
        return new ResponseEntity<>(userTrackingDTO, HttpStatus.CREATED);
    }

    @PutMapping(path = PATH_USERS_WITH_UID)
    public HttpEntity<UserTrackingDTO> updateUserTrackingById(
            @PathVariable(UID) @NotBlank @NotNull final String uid,
            @Valid @RequestBody final UserTrackingBareDTO userBareDTO) {

        final UserTrackingDTO userTrackingBareDTO = userTrackingCommandService.updateUserTrackingById(
                uid,
                userBareDTO
        );
        return new ResponseEntity<>(userTrackingBareDTO, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = PATH_USERS_WITH_UID)
    public HttpEntity<ResponseMessageDTO> deleteUserTrackingById(
            @PathVariable(UID) final String uid) {

        final ResponseMessageDTO messageDTO = userTrackingCommandService.deleteUserTrackingById(
                uid
        );
        return new ResponseEntity<>(messageDTO, HttpStatus.OK);
    }


}