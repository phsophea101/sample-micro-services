package com.example.gateway.enums;

import com.example.gateway.common.consts.ErrorCodeType;

public enum BizErrorCode implements ErrorCodeType {

    /**
     * Error General exception.
     */
    E0001("E0001", "General exception error."),
    E0002("E0002", "Record not found."),
    E0003("E0003", "Record already existed."),
    ;

    final String value;
    final String description;

    private BizErrorCode(String value, String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getDescription() {
        return description;
    }

}
