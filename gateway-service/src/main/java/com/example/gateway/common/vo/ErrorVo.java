package com.example.gateway.common.vo;

import com.example.gateway.common.util.ContextUtil;
import org.apache.commons.lang3.ObjectUtils;

public class ErrorVo {
    private String code;
    private String message;
    private String description;

    public ErrorVo(String code, String message) {
        this.code = code;
        this.message = message;
        this.description = message;
    }

    public ErrorVo(String code, String message, String description) {
        this.code = code;
        this.message = message;
        if (ContextUtil.isProfile("dev", "local", "test", "debug"))
            this.description = description;
        else
            this.description = this.message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return ObjectUtils.isEmpty(description) ? message : description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
