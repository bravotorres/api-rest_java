package com.quironlabs.api.config.responses;

import java.util.UUID;


public class GeneralResponse {
    private Integer code;
    private UUID folio;
    private String message;

    public GeneralResponse(Integer code, UUID folio, String message) {
        this.code = code;
        this.folio = folio;
        this.message = message;
    }
    
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public UUID getFolio() {
        return folio;
    }

    public void setFolio(UUID folio) {
        this.folio = folio;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
