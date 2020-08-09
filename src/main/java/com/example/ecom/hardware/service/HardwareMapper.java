package com.example.ecom.hardware.service;

import com.demo.usertracking.api.swagger.model.hardware.HardwareBareDTO;
import com.demo.usertracking.api.swagger.model.hardware.HardwareDTO;
import com.example.ecom.hardware.entity.Hardware;

public class HardwareMapper {


    public static HardwareDTO mapToHardwareDTO(final Hardware hardware,
                                               final HardwareBareDTO data) {

        final HardwareDTO hardwareDTO = new HardwareDTO();
        hardwareDTO.setData(data);
        hardwareDTO.setId(hardware.getHardwareId());
        return hardwareDTO;
    }

    public static HardwareBareDTO mapToHardwareBareDTO(final Hardware hardware) {

        final HardwareBareDTO data = new HardwareBareDTO();
        data.setManufacture(hardware.getManufacture());
        data.setMemory(hardware.getMemory());
        data.setModel(hardware.getModel());
        data.setProcessor(hardware.getProcessor());
        data.setVirtualization(hardware.getVirtualization());
        return data;
    }

    public static Hardware mapToHardware(HardwareBareDTO userBareDTO) {

        Hardware hardware = new Hardware();
        hardware.setManufacture(userBareDTO.getManufacture());
        hardware.setMemory(userBareDTO.getMemory());
        hardware.setModel(userBareDTO.getModel());
        hardware.setProcessor(userBareDTO.getProcessor());
        hardware.setVirtualization(userBareDTO.getVirtualization());
        return hardware;
    }

    public static Hardware mapToUpdateHardware(
            final Hardware hardware,
            final HardwareBareDTO userBareDTO) {

        hardware.setManufacture(userBareDTO.getManufacture());
        hardware.setMemory(userBareDTO.getMemory());
        hardware.setModel(userBareDTO.getModel());
        hardware.setProcessor(userBareDTO.getProcessor());
        hardware.setVirtualization(userBareDTO.getVirtualization());
        return hardware;
    }
}
