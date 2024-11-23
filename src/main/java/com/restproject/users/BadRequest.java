package com.restproject.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BadRequest {

    private String responseCode;
    private String responseMessage;
}
