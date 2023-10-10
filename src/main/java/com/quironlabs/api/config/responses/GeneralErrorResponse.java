package com.quironlabs.api.config.responses;


import java.util.List;
import java.util.UUID;

public class GeneralErrorResponse extends GeneralResponse {
    private List<?> details;

    public GeneralErrorResponse(Integer code, UUID folio, String message, List<?> details) {
        super(code, folio, message);
        this.details = details;
    }

    public List<?> getDetails() {
        return details;
    }

    public void setDetails(List<Object> details) {
        this.details = details;
    }

}
