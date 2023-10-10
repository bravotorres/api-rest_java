package com.quironlabs.api.config.responses;

import java.util.List;
import java.util.UUID;


public class InternalServerErrorResponse extends GeneralErrorResponse {
    public InternalServerErrorResponse(List<?> details) {
        this(500, UUID.randomUUID(), ResponseMessages.HTTP_500, details);
    }

    public InternalServerErrorResponse(Integer code, UUID folio, String message, List<?> details) {
        super(code, folio, message, details);
        // TODO Auto-generated constructor stub
    }

}
