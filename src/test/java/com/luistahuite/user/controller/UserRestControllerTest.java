package com.luistahuite.user.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.luistahuite.user.dto.PhoneRequest;
import com.luistahuite.user.dto.UserRequest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class UserRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testCreate() throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("Luis");
        userRequest.setEmail("luis@example.com");
        userRequest.setPassword("pass");

        PhoneRequest phoneRequest = new PhoneRequest();
        phoneRequest.setNumber(123456);
        phoneRequest.setCitycode(1);
        phoneRequest.setContrycode(502);
        List<PhoneRequest> phoneRequestList = new ArrayList<>();
        phoneRequestList.add(phoneRequest);
        userRequest.setPhones(phoneRequestList);

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    void testCreateValidateEmailExist() throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("Luis");
        userRequest.setEmail("luis@example.com");
        userRequest.setPassword("pass");

        PhoneRequest phoneRequest = new PhoneRequest();
        phoneRequest.setNumber(123456);
        phoneRequest.setCitycode(1);
        phoneRequest.setContrycode(502);
        List<PhoneRequest> phoneRequestList = new ArrayList<>();
        phoneRequestList.add(phoneRequest);
        userRequest.setPhones(phoneRequestList);

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)));

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isPreconditionFailed());
    }


    @Test
    void testCreateValidateEmailNoExist() throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("Luis");
        userRequest.setEmail("luis@example.com");
        userRequest.setPassword("pass");

        PhoneRequest phoneRequest = new PhoneRequest();
        phoneRequest.setNumber(123456);
        phoneRequest.setCitycode(1);
        phoneRequest.setContrycode(502);
        List<PhoneRequest> phoneRequestList = new ArrayList<>();
        phoneRequestList.add(phoneRequest);
        userRequest.setPhones(phoneRequestList);

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequest)));


        UserRequest userRequest2 = new UserRequest();
        userRequest2.setName("Ricardo");
        userRequest2.setEmail("luis2@example.com");
        userRequest2.setPassword("pass");

        PhoneRequest phoneRequest2 = new PhoneRequest();
        phoneRequest2.setNumber(123456);
        phoneRequest2.setCitycode(1);
        phoneRequest2.setContrycode(502);
        List<PhoneRequest> phoneRequestList2 = new ArrayList<>();
        phoneRequestList2.add(phoneRequest2);
        userRequest2.setPhones(phoneRequestList2);

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest2)))
                .andExpect(status().isCreated());
    }

    @Test
    void testCreateValidatePasswordNoMatch() throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("Luis");
        userRequest.setEmail("luis@example.com");
        userRequest.setPassword("PASS");

        PhoneRequest phoneRequest = new PhoneRequest();
        phoneRequest.setNumber(123456);
        phoneRequest.setCitycode(1);
        phoneRequest.setContrycode(502);
        List<PhoneRequest> phoneRequestList = new ArrayList<>();
        phoneRequestList.add(phoneRequest);
        userRequest.setPhones(phoneRequestList);

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isPreconditionFailed());
    }

}
