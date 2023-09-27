package com.sansa.entretainmentapi.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateResponse<T> {
    private String message;
    private T updated;

    public UpdateResponse(String message, T updated) {
        this.message = message;
        this.updated = updated;
    }
}
