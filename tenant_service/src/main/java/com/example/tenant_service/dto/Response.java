package com.example.tenant_service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Response {

    private String code;
    private String message;
    private boolean success;
    private Object data;


    public Response(String code, String message, boolean success,Object data) {
        this.code = code;
        this.message = message;
        this.success = success;
        this.data = data;
    }


}
