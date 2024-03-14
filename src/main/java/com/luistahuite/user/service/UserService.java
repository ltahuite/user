package com.luistahuite.user.service;

import com.luistahuite.user.common.UserRequestMapper;
import com.luistahuite.user.dto.PhoneRequest;
import com.luistahuite.user.dto.UserRequest;
import com.luistahuite.user.entities.Phone;
import com.luistahuite.user.entities.Regex;
import com.luistahuite.user.entities.User;
import com.luistahuite.user.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRequestMapper userRequestMapper;
    private final PhoneService phoneService;
    private final RegexService regexService;

    private static final String regexEmail = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private static final String regexPassword = "\\p{javaLowerCase}+";
    private static final String regexTypeEmail = "email";
    private static final String regexTypePassword = "password";


    @Autowired
    public UserService(UserRepository userRepository, UserRequestMapper userRequestMapper, PhoneService phoneService, RegexService regexService) {
        this.userRepository = userRepository;
        this.userRequestMapper = userRequestMapper;
        this.phoneService = phoneService;
        this.regexService = regexService;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }


    public User save(UserRequest userRequest) {
        User user = userRequestMapper.UserRequestToUser(userRequest);
        user.setToken(getJWTToken(userRequest.getEmail()));
        user = userRepository.save(user);
        for (PhoneRequest p : userRequest.getPhones()) {
            Phone phone = new Phone();
            phone.setUserId(user.getId());
            phone.setNumber(p.getNumber());
            phone.setCitycode(p.getCitycode());
            phone.setContrycode(p.getContrycode());
            phoneService.save(phone);
        }
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

    public Boolean validateRegexEmail(String email) {
        String regex = regexEmail;
        Optional<Regex> optionalRegex = regexService.findByType(regexTypeEmail);
        if (optionalRegex.isPresent()) {
            regex = optionalRegex.get().getExpression();
        }

        return Pattern.matches(regex, email);
    }

    public Boolean validateRegexPassword(String password) {
        String regex = regexPassword;
        Optional<Regex> optionalRegex = regexService.findByType(regexTypePassword);
        if (optionalRegex.isPresent()) {
            regex = optionalRegex.get().getExpression();
        }

        return Pattern.matches(regex, password);
    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        return Jwts
                .builder()
                .setId("LRT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();
    }
}
