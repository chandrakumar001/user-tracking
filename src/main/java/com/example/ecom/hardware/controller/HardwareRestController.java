package com.example.ecom.hardware.controller;

import com.demo.usertracking.api.swagger.model.ResponseMessageDTO;
import com.demo.usertracking.api.swagger.model.hardware.HardwareBareDTO;
import com.demo.usertracking.api.swagger.model.hardware.HardwareDTO;
import com.demo.usertracking.api.swagger.model.hardware.HardwareListResponseDTO;
import com.example.ecom.hardware.service.HardwareService;
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
@Api("Hardware API")
@AllArgsConstructor(access = PACKAGE, onConstructor = @__(@Autowired))
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class HardwareRestController {

    public static final String PATH_HARDWARE = "/hardware";
    public static final String PATH_HARDWARE_WITH_HID = "/hardware/{hid}";
    public static final String HID = "hid";

    @NonNull HardwareService hardwareService;

    @GetMapping(path = PATH_HARDWARE)
    public HttpEntity<HardwareListResponseDTO> getAllHardware() {

        final HardwareListResponseDTO usersListResponseDTO = hardwareService.getAllHardware();
        return new ResponseEntity<>(usersListResponseDTO, HttpStatus.OK);
    }

    @PostMapping(path = PATH_HARDWARE)
    public HttpEntity<HardwareDTO> createHardware(
            @Valid @RequestBody final HardwareBareDTO userBareDTO) {

        final HardwareDTO hardwareDTO = hardwareService.createHardware(
                userBareDTO
        );
        return new ResponseEntity<>(hardwareDTO, HttpStatus.CREATED);
    }

    @PutMapping(path = PATH_HARDWARE_WITH_HID)
    public HttpEntity<HardwareDTO> updateHardware(
            @PathVariable(HID) final String hid,
            @Valid @RequestBody final HardwareBareDTO userBareDTO) {

        final HardwareDTO hardwareDTO = hardwareService.updateHardware(
                hid,
                userBareDTO
        );
        return new ResponseEntity<>(hardwareDTO, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = PATH_HARDWARE_WITH_HID)
    public HttpEntity<HardwareDTO> getHardwareById(
            @PathVariable(HID) final String hid) {

        final HardwareDTO hardwareDTO = hardwareService.getHardwareById(
                hid
        );
        return new ResponseEntity<>(hardwareDTO, HttpStatus.OK);
    }

    @DeleteMapping(path = PATH_HARDWARE_WITH_HID)
    public HttpEntity<ResponseMessageDTO> deleteHardware(
            @PathVariable(HID) final String hid) {

        final ResponseMessageDTO responseMessageDTO = hardwareService.deleteHardware(
                hid
        );
        return new ResponseEntity<>(responseMessageDTO, HttpStatus.OK);
    }


}