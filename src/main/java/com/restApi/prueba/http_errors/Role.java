package com.restApi.prueba.http_errors;

public enum Role {
    ADMIN,

    ESTUDIANTE,
    PROFESOR,
    USUARIO,

    MANAGER,
    OPERATOR,
    CUSTOMER;

    public static final String PREFIX = "ROLE_"; 

    public static Role of(String withPrefix) {
        return Role.valueOf(withPrefix.replace(Role.PREFIX, ""));
    }

    public String withPrefix() {
        return PREFIX + this;
    }

}
