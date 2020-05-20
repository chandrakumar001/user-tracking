package com.example.ecom.usertracking.resource;

import com.demo.usertracking.api.swagger.model.UserTrackingDTO;
import com.demo.usertracking.api.swagger.model.UserTrackingListResponseDTO;
import com.example.ecom.usertracking.service.UserTrackingQueryService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;


@RestController
@Api("User Tracking API")
@AllArgsConstructor(access = PACKAGE, onConstructor = @__(@Autowired))
@FieldDefaults(level = PRIVATE, makeFinal = true)
@ConditionalOnProperty(name = "app.write.enabled", havingValue = "false")
public class UserTrackingQueryRestController {

    public static final String PATH_USERS = "/users";
    public static final String PATH_USERS_WITH_UID = PATH_USERS + "/{uid}";
    public static final String UID = "uid";
    @NonNull UserTrackingQueryService userTrackingQueryService;

    @GetMapping(path = PATH_USERS)
    public HttpEntity<UserTrackingListResponseDTO> getAllUserTracking() {

        final UserTrackingListResponseDTO usersListResponseDTO = new UserTrackingListResponseDTO();
        return new ResponseEntity<>(usersListResponseDTO, HttpStatus.OK);
    }

    @GetMapping(path = PATH_USERS_WITH_UID)
    public HttpEntity<UserTrackingDTO> getUserTracking(
            @PathVariable(UID) @NotBlank @NotNull  final String uid) {

        System.out.println(uid + "-----");
        final UserTrackingDTO userDTO = new UserTrackingDTO();
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}