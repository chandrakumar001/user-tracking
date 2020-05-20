package com.example.ecom.usertracking.mapper;

import com.demo.usertracking.api.swagger.model.AuditModelDTO;
import com.demo.usertracking.api.swagger.model.ContactInfoDTO;
import com.demo.usertracking.api.swagger.model.UserTrackingBareDTO;
import com.demo.usertracking.api.swagger.model.UserTrackingDTO;
import com.example.ecom.usertracking.entity.UserTracking;

import java.util.List;

public class UserTrackingMapper {

    public static UserTrackingDTO mapToUserTrackingDTO(
            final UserTracking newUserTracking,
            final List<ContactInfoDTO> contactInfoDTOList) {

        final UserTrackingBareDTO userTrackingBareDTO2 = mapToUserTrackingBareDTO(
                newUserTracking,
                contactInfoDTOList
        );
        UserTrackingDTO userTrackingDTO = new UserTrackingDTO();
        userTrackingDTO.setData(userTrackingBareDTO2);
        userTrackingDTO.setId(newUserTracking.getUserTrackingId());
        return userTrackingDTO;
    }

    public static UserTrackingBareDTO mapToUserTrackingBareDTO(
            final UserTracking newUserTracking,
            final List<ContactInfoDTO> contactInfoDTOList) {

        final AuditModelDTO auditModelDTO = AuditModelMapper.getAuditModelDTO(
                newUserTracking
        );

        final UserTrackingBareDTO userTrackingBareDTO = new UserTrackingBareDTO();
        userTrackingBareDTO.setAuditModel(auditModelDTO);
        userTrackingBareDTO.setContactInfo(contactInfoDTOList);
        userTrackingBareDTO.setEmail(newUserTracking.getEmail());
        userTrackingBareDTO.setUserNumber(newUserTracking.getUserNumber());
        return userTrackingBareDTO;
    }
}
