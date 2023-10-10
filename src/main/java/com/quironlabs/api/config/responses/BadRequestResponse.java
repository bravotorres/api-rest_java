package com.quironlabs.api.config.responses;


import java.util.List;
import java.util.UUID;

public class BadRequestResponse extends GeneralErrorResponse {
    
    public BadRequestResponse(List<?> details) {
        this(400, UUID.randomUUID(), ResponseMessages.HTTP_400, details);
    }
    
    public BadRequestResponse(Integer code, UUID folio, String message, List<?> details) {
        super(code, folio, message, details);
        // TODO Auto-generated constructor stub
    }

}
