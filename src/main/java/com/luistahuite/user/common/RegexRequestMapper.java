package com.luistahuite.user.common;

import com.luistahuite.user.dto.RegexRequest;
import com.luistahuite.user.entities.Regex;
import org.springframework.stereotype.Component;


@Component
public class RegexRequestMapper {

    public Regex RegexRequestToRegex(RegexRequest regexRequest) {
        Regex regex = new Regex();
        regex.setType(regexRequest.getType());
        regex.setExpression(regexRequest.getExpression());
        return regex;
    }}
