package com.example.ecom.usertracking.repository;

import com.example.ecom.usertracking.entity.UserTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserTrackingRepository extends JpaRepository<UserTracking, UUID> {

    Optional<UserTracking> findByEmail(String email);

    Optional<UserTracking> findByUserTrackingIdAndEmail(
            final UUID id,
            final String email
    );


    //Soft delete.
//    @Modifying
//    void softDelete(final UUID sidUUID);
}
