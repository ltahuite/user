package com.luistahuite.user.service;

import com.luistahuite.user.common.UserRequestMapper;
import com.luistahuite.user.dto.PhoneRequest;
import com.luistahuite.user.dto.UserRequest;
import com.luistahuite.user.entities.Phone;
import com.luistahuite.user.entities.User;
import com.luistahuite.user.repository.PhoneRepository;
import com.luistahuite.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRequestMapper userRequestMapper;
    private final PhoneService phoneService;

    private static String regexEmail = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$";
    private static String regexPassword = "";


    @Autowired
    public UserService(UserRepository userRepository, UserRequestMapper userRequestMapper, PhoneService phoneService) {
        this.userRepository = userRepository;
        this.userRequestMapper = userRequestMapper;
        this.phoneService = phoneService;
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }


    public User save(UserRequest userRequest) {
        User user = userRequestMapper.UserRequestToUser(userRequest);

        user = userRepository.save(user);
        for (PhoneRequest p : userRequest.getPhones()) {
            Phone phone = new Phone();
            phone.setUserId(user.getId());
            phone.setNumber(p.getNumber());
            phone.setCitycode(p.getCitycode());
            phone.setContrycode(p.getContrycode());
            phoneService.save(phone);
        }
        List<Phone> phones = phoneService.findAll();
        return user;

    }

    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    public Optional<User> update(UUID id, UserRequest userRequest) {
        Optional<User> optionalUser = findById(id);
        if (optionalUser.isPresent()) {
            User updateUser = optionalUser.get();
            updateUser = userRequestMapper.UserRequestToUser(userRequest);
            userRepository.save(updateUser);
        }
        return optionalUser;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Boolean validateRegex(String regex, String value) {
        return Pattern.matches(regex, value);
    }

    public Boolean validateRegexEmail(String password) {
        String regex = regexEmail;

        return Pattern.matches(regex, password);
    }

}
