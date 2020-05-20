package com.example.ecom.usertracking.service;

import com.demo.usertracking.api.swagger.model.ContactInfoDTO;
import com.demo.usertracking.api.swagger.model.ResponseMessageDTO;
import com.demo.usertracking.api.swagger.model.UserTrackingBareDTO;
import com.demo.usertracking.api.swagger.model.UserTrackingDTO;
import com.example.ecom.exception.FieldConflictException;
import com.example.ecom.exception.ResourceNotFoundException;
import com.example.ecom.usertracking.entity.ContactInfo;
import com.example.ecom.usertracking.entity.UserTracking;
import com.example.ecom.usertracking.mapper.ContactInfoMapper;
import com.example.ecom.usertracking.mapper.UserTrackingMapper;
import com.example.ecom.usertracking.repository.UserTrackingRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor(access = PACKAGE, onConstructor = @__(@Autowired))
@FieldDefaults(level = PRIVATE, makeFinal = true)
@Transactional
public class UserTrackingCommandService {
    private static final String USER_NOT_FOUND_ID = "user id not found";
    private static final String USER_EMAIl_NOT_FOUND_ID = "user id or email  not found";

/*    void createOrder(int userIndex, int productIndex);
    void cancelOrder(long orderId);
    		* Deposit Delete
*/

    @NonNull UserTrackingRepository userTrackingRepository;

    public UserTrackingDTO createUserTracking(final UserTrackingBareDTO userTrackingBareDTO) {

        //validate email
        final boolean isEmailExists = getByEmail(userTrackingBareDTO.getEmail())
                .isPresent();
        if (isEmailExists) {
            throw new FieldConflictException("already exist email");
        }

        final UserTracking userTracking = new UserTracking();
        userTracking.setEmail(userTrackingBareDTO.getEmail());
        userTracking.setSelectedPlan(userTrackingBareDTO.getSelectedPlan());
        userTracking.setUserNumber(userTrackingBareDTO.getUserNumber());

        List<ContactInfo> contactInfos = mapToContactInfoList(
                userTrackingBareDTO.getContactInfo(),
                userTracking
        );
        userTracking.setContactInfos(contactInfos);

        final UserTracking newUserTracking = userTrackingRepository.save(
                userTracking
        );

        final List<ContactInfoDTO> contactInfoDTOList = UserTrackingCommandService.mapToContactInfoDTOList(
                newUserTracking.getContactInfos()
        );
        return UserTrackingMapper.mapToUserTrackingDTO(
                newUserTracking,
                contactInfoDTOList
        );
    }

    public Optional<UserTracking> getByEmail(String email) {
        return userTrackingRepository.findByEmail(email);
    }

    public ResponseMessageDTO deleteUserTrackingById(final String uid) {

        final UUID id = UUID.fromString(uid);
        getUserTracking(id);

        userTrackingRepository.deleteById(id);

        final ResponseMessageDTO responseMessageDTO = new ResponseMessageDTO();
        responseMessageDTO.setMessage("Successfully deleted");
        return responseMessageDTO;
    }

    public UserTrackingDTO updateUserTrackingById(
            final String uid,
            final UserTrackingBareDTO userTrackingBareDTO) {

        final UUID id = UUID.fromString(uid);
        // validate id
        final UserTracking userTracking = getUserTracking(
                id,
                userTrackingBareDTO.getEmail()
        );
        //validate email


        userTracking.setEmail(userTrackingBareDTO.getEmail());
        userTracking.setSelectedPlan(userTrackingBareDTO.getSelectedPlan());
        userTracking.setUserNumber(userTrackingBareDTO.getUserNumber());

        List<ContactInfo> contactInfoList = mapToContactInfoList(
                userTrackingBareDTO.getContactInfo(),
                userTracking
        );
        userTracking.setContactInfos(contactInfoList);

        final UserTracking newUserTracking = userTrackingRepository.save(
                userTracking
        );

        final List<ContactInfoDTO> contactInfoDTOList = UserTrackingCommandService.mapToContactInfoDTOList(
                newUserTracking.getContactInfos()
        );
        return UserTrackingMapper.mapToUserTrackingDTO(
                newUserTracking,
                contactInfoDTOList
        );
    }

    private UserTracking getUserTracking(UUID id) {
        return getById(id)
                .orElseThrow(UserTrackingCommandService::userIdNotFoundException);
    }

    private UserTracking getUserTracking(UUID id, String email) {

        return userTrackingRepository.findByUserTrackingIdAndEmail(
                id,
                email
        ).orElseThrow(UserTrackingCommandService::userIdAndEmailResourceNotFoundException);
    }

    private Optional<UserTracking> getById(UUID id) {
        return userTrackingRepository.findById(id);
    }


    private static List<ContactInfoDTO> mapToContactInfoDTOList(
            List<ContactInfo> contactInfoList) {
        return contactInfoList
                .stream()
                .map(ContactInfoMapper::mapToContactInfoDTO)
                .collect(Collectors.toList());
    }


    private List<ContactInfo> mapToContactInfoList(final List<ContactInfoDTO> contactInfoDTOList,
                                                   final UserTracking userTracking) {
        return contactInfoDTOList
                .stream()
                .map(contactInfoDTO -> ContactInfoMapper.mapToContactInfo(
                        userTracking,
                        contactInfoDTO
                ))
                .collect(Collectors.toList());
    }

    private static ResourceNotFoundException userIdNotFoundException() {
        return new ResourceNotFoundException(USER_NOT_FOUND_ID);
    }

    private static ResourceNotFoundException userIdAndEmailResourceNotFoundException() {
        return new ResourceNotFoundException(USER_EMAIl_NOT_FOUND_ID);
    }
}
