package com.luistahuite.user.repository;

import com.luistahuite.user.entities.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PhoneRepository extends JpaRepository<Phone, UUID> {
    List<Phone> findAllByUserId(UUID userId);
}
