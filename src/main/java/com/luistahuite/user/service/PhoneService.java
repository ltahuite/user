package com.luistahuite.user.service;

import com.luistahuite.user.common.UserRequestMapper;
import com.luistahuite.user.entities.Phone;
import com.luistahuite.user.repository.PhoneRepository;
import com.luistahuite.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PhoneService {
    private final PhoneRepository phoneRepository;

    @Autowired
    public PhoneService(PhoneRepository phoneRepository) {

        this.phoneRepository = phoneRepository;
    }

    public List<Phone> findAllByUserId(UUID userId) {
        return phoneRepository.findAllByUserId(userId);
    }

    public Phone save(Phone phone) {
        return phoneRepository.save(phone);
    }

    public List<Phone> findAll() {
        return phoneRepository.findAll();
    }
}
