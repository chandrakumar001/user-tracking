package com.example.ecom.hardware.repositories;

import com.example.ecom.hardware.entity.Hardware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HardwareRepository extends JpaRepository<Hardware, UUID> {
}
