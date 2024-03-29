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

import java.util.Calendar;
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

    private static final String REGEXEMAIL = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private static final String REGEXPASSWORD = "\\p{javaLowerCase}+";
    private static final String REGEXTYPEEMAIL = "email";
    private static final String REGEXTYPEPASSWORD = "password";


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
        User user = userRequestMapper.userRequestToUser(userRequest);
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
            updateUser.setName(userRequest.getName());
            updateUser.setEmail(userRequest.getEmail());
            updateUser.setPassword(userRequest.getPassword());
            updateUser.setId(optionalUser.get().getId());
            Calendar cal = Calendar.getInstance();
            updateUser.setModified(cal.getTime());
            userRepository.save(updateUser);
        }
        return optionalUser;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Boolean validateRegexEmail(String email) {
        String regex = REGEXEMAIL;
        Optional<Regex> optionalRegex = regexService.findByType(REGEXTYPEEMAIL);
        if (optionalRegex.isPresent()) {
            regex = optionalRegex.get().getExpression();
        }

        return Pattern.matches(regex, email);
    }

    public Boolean validateRegexPassword(String password) {
        String regex = REGEXPASSWORD;
        Optional<Regex> optionalRegex = regexService.findByType(REGEXTYPEPASSWORD);
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
