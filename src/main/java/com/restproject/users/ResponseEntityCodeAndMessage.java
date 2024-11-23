package com.restproject.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ResponseEntityCodeAndMessage {

    private String responseCode;
    private String responseMessage;
}
