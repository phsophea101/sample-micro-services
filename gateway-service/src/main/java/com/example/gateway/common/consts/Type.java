package com.example.gateway.common.consts;

import com.fasterxml.jackson.annotation.JsonValue;

public interface Type<T> {
    @JsonValue
    T getValue();

    String getDescription();
}

