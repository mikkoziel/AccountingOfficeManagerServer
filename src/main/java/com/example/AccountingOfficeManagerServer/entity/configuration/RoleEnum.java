package com.example.AccountingOfficeManagerServer.entity.configuration;

import com.example.AccountingOfficeManagerServer.entity.model.Role;

public enum RoleEnum {
    ADMIN(Roles.ADMIN),
    USER(Roles.USER),
    CLIENT(Roles.CLIENT),
    AO_ADMIN(Roles.AO_ADMIN);

    public class Roles{
        public static final String ADMIN = "ADMIN";
        public static final String USER = "USER";
        public static final String CLIENT = "CLIENT";
        public static final String AO_ADMIN = "AO_ADMIN";
    }

    private final String label;

    private RoleEnum(String label) {
        this.label = label;
    }

    public String toString() {
        return this.label;
    }
}