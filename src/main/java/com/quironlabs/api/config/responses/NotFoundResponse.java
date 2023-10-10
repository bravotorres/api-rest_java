package com.quironlabs.api.config.responses;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class NotFoundResponse extends GeneralErrorResponse {
    private static final Integer code = 404;

    public NotFoundResponse() {
        this(new ArrayList<>());
    }

    public NotFoundResponse(List<?> details) {
        this(UUID.randomUUID(), ResponseMessages.HTTP_404, details);
    }

    public NotFoundResponse(UUID uuid, List<?> details) {
        this(uuid, ResponseMessages.HTTP_404, details);
    }

    public NotFoundResponse(UUID folio, String message, List<?> details) {
        super(code, folio, message, details);
    }
}
