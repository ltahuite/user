package com.luistahuite.user.repository;

import com.luistahuite.user.entities.Regex;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RegexRepository extends JpaRepository<Regex, UUID> {

    Optional<Regex> findFirstByType(String type);

}
