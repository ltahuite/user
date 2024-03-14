package com.luistahuite.user.service;

import com.luistahuite.user.entities.Regex;
import com.luistahuite.user.repository.RegexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegexService {
    private final RegexRepository regexRepository;

    @Autowired
    public RegexService(RegexRepository regexRepository) {

        this.regexRepository = regexRepository;
    }


    public Optional<Regex> findByType(String type) {
        return regexRepository.findFirstByType(type);
    }

    public Regex save(Regex regex) {
        return regexRepository.save(regex);
    }

}
