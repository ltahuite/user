package com.luistahuite.user.common;

import com.luistahuite.user.dto.PhoneRequest;
import com.luistahuite.user.entities.Phone;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PhoneRequestMapper {
    public Phone PhoneRequestToPhone(PhoneRequest phoneRequest) {
        Phone phone = new Phone();
        phone.setNumber(phoneRequest.getNumber());
        phone.setCitycode(phoneRequest.getCitycode());
        phone.setContrycode(phoneRequest.getContrycode());
        return phone;
    }

    public List<Phone> PhoneListRequestToPhoneList(List<PhoneRequest> phonesRequest) {
        List<Phone> phones = new ArrayList<>();
        phonesRequest.forEach(p -> {
            Phone phone = new Phone();
            phone.setNumber(p.getNumber());
            phone.setCitycode(p.getCitycode());
            phone.setContrycode(p.getContrycode());
            phones.add(phone);
        });

        return phones;
    }

}
