package com.sansa.entretainmentapi.util;

import org.springframework.stereotype.Component;

@Component
public class UUIDChecker {
    public boolean isUUID(String uuid) {
        try {
            java.util.UUID.fromString(uuid);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
