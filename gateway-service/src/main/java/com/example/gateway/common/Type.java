package com.example.gateway.common;

import com.fasterxml.jackson.annotation.JsonValue;

public interface Type<T> {
    @JsonValue
    T getValue();

    String getDescription();
}

