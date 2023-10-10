package com.quironlabs.api.config.responses;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class ForbiddenResponse extends GeneralErrorResponse {
    private static final Integer code = 403;
    
    public ForbiddenResponse() {
        this(new ArrayList<>());
    }

    public ForbiddenResponse(List<?> details) {
        this(UUID.randomUUID(), ResponseMessages.HTTP_403, details);
    }
    
    public ForbiddenResponse(UUID uuid, List<?> details) {
        this(uuid, ResponseMessages.HTTP_403, details);
    }

    public ForbiddenResponse(UUID folio, String message, List<?> details) {
        super(code, folio, message, details);
    }

}
