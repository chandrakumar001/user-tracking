package com.example.ecom.usertracking.mapper;

import com.demo.usertracking.api.swagger.model.AuditModelDTO;
import com.example.ecom.usertracking.entity.UserTracking;

public class AuditModelMapper {

    public static AuditModelDTO getAuditModelDTO(final UserTracking newUserTracking) {

        final AuditModelDTO auditModelDTO = new AuditModelDTO();
        auditModelDTO.setUpdateDate(newUserTracking.getLastModifiedDate().toString());
        //auditModelDTO.setCreatedDate(newUserTracking.getCreationDate().toString());
        return auditModelDTO;
    }
}
