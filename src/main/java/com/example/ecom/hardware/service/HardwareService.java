package com.example.ecom.hardware.service;

import com.demo.usertracking.api.swagger.model.ResponseMessageDTO;
import com.demo.usertracking.api.swagger.model.hardware.HardwareBareDTO;
import com.demo.usertracking.api.swagger.model.hardware.HardwareDTO;
import com.demo.usertracking.api.swagger.model.hardware.HardwareListResponseDTO;
import com.example.ecom.exception.FieldValidationException;
import com.example.ecom.exception.ResourceNotFoundException;
import com.example.ecom.hardware.UniqueIdentifierValidator;
import com.example.ecom.hardware.entity.Hardware;
import com.example.ecom.hardware.repositories.HardwareRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Service
@Transactional
@AllArgsConstructor(access = PACKAGE, onConstructor = @__(@Autowired))
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class HardwareService {

    private static final String ERROR_HARDWARE_ID_EMPTY = "hardware.id.empty";
    private static final String ERROR_HARDWARE_ID_INVALID = "hardware.id.invalid";
    private static final String ERROR_HARDWARE_ID_NOT_FOUND = "hardware.not.found.id";

    @NonNull HardwareRepository hardwareRepository;

    public ResponseMessageDTO deleteHardware(String hid) {
        validateHid(hid);
        final UUID hidUUID = UUID.fromString(hid);

        hardwareIdExists(hidUUID);

        hardwareRepository.deleteById(hidUUID);
        final ResponseMessageDTO responseMessageDTO = new ResponseMessageDTO();
        responseMessageDTO.setMessage("successfully deleted");
        return responseMessageDTO;
    }

    private void validateHid(String hid) {

        final Optional<String> errorMessage = UniqueIdentifierValidator.validateUUIDFormat(
                hid,
                ERROR_HARDWARE_ID_EMPTY,
                ERROR_HARDWARE_ID_INVALID
        );
        if (errorMessage.isPresent()) {
            throw new FieldValidationException(errorMessage.get());
        }
    }

    public HardwareDTO getHardwareById(String hid) {

        validateHid(hid);
        final UUID hidUUID = UUID.fromString(hid);

        final Hardware hardware = hardwareIdExists(
                hidUUID
        );
        final HardwareBareDTO data = HardwareMapper.mapToHardwareBareDTO(
                hardware
        );
        return HardwareMapper.mapToHardwareDTO(hardware, data);
    }

    private Hardware hardwareIdExists(final UUID hidUUID) {
        return hardwareRepository.findById(hidUUID)
                .orElseThrow(this::hardwareIdNotFoundException);
    }

    private ResourceNotFoundException hardwareIdNotFoundException() {
        return new ResourceNotFoundException(ERROR_HARDWARE_ID_NOT_FOUND);
    }

    public HardwareListResponseDTO getAllHardware() {

        return null;
    }

    public HardwareDTO updateHardware(String hid,
                                      HardwareBareDTO userBareDTO) {
        validateHid(hid);
        final UUID hidUUID = UUID.fromString(hid);

        final Hardware hardware = hardwareIdExists(
                hidUUID
        );

        final HardwareBareDTO data = HardwareMapper.mapToHardwareBareDTO(
                hardware
        );
        return HardwareMapper.mapToHardwareDTO(hardware, data);
    }

    public HardwareDTO createHardware(HardwareBareDTO userBareDTO) {

        final Hardware hardware = HardwareMapper.mapToHardware(
                userBareDTO
        );

        final Hardware newHardware = hardwareRepository.save(hardware);
        final HardwareBareDTO data = HardwareMapper.mapToHardwareBareDTO(
                newHardware
        );
        return HardwareMapper.mapToHardwareDTO(newHardware, data);
    }
}
