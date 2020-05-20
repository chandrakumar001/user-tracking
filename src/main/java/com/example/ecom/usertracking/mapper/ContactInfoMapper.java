package com.example.ecom.usertracking.mapper;

import com.demo.usertracking.api.swagger.model.ContactInfoDTO;
import com.example.ecom.usertracking.entity.ContactInfo;
import com.example.ecom.usertracking.entity.UserTracking;

public class ContactInfoMapper {

    public static ContactInfo mapToContactInfo(final UserTracking userTracking,
                                               final ContactInfoDTO contactInfoDTO) {
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setFirstName(contactInfoDTO.getFirstName());
        contactInfo.setLastName(contactInfoDTO.getLastName());
        contactInfo.setPhone(contactInfoDTO.getPhone());
        contactInfo.setUserTracking(userTracking);
        return contactInfo;
    }

    public static ContactInfoDTO mapToContactInfoDTO(ContactInfo contactInfo) {

        ContactInfoDTO contactInfoDTO = new ContactInfoDTO();
        contactInfoDTO.setPhone(contactInfo.getPhone());
        contactInfoDTO.setLastName(contactInfo.getLastName());
        contactInfoDTO.setFirstName(contactInfo.getFirstName());
        return contactInfoDTO;
    }
}
