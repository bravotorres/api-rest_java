package com.quironlabs.api.config.responses;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class UnauthorizedResponse extends GeneralErrorResponse {
    private static final Integer code = 401;

    public UnauthorizedResponse() {
        this(new ArrayList<>());
    }

    public UnauthorizedResponse(List<?> details) {
        this(UUID.randomUUID(), ResponseMessages.HTTP_401, details);
    }

    public UnauthorizedResponse(UUID uuid, List<?> details) {
        this(uuid, ResponseMessages.HTTP_401, details);
    }

    public UnauthorizedResponse(UUID folio, String message, List<?> details) {
        super(code, folio, message, details);
    }

}
