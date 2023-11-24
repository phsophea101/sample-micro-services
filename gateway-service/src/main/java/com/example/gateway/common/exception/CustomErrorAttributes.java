package com.example.gateway.common.exception;

import com.example.gateway.common.util.ContextUtil;
import com.example.gateway.common.util.I18nUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@Configuration
@AllArgsConstructor
@Slf4j
public class CustomErrorAttributes extends DefaultErrorAttributes {
    private final MessageSource messageSource;

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, options);
        Object exception = webRequest.getAttribute(ErrorAttributes.ERROR_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);
        int status = (int) map.get("status");
        String message = (String) map.get("message");
        String description = (String) map.get("description");
        String path = (String) map.get("path");
        message = StringUtils.isEmpty(message) ? (String) map.get("error") : message;
        String code = String.format("E0%s", status);
        if (exception instanceof BizException) {
            code = ((BizException) exception).getError().getValue();
            message = ((Exception) exception).getMessage();
        } else
            message = I18nUtils.resolveMessage(code, message);
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> error = new HashMap<>();
        response.put("status", String.valueOf(status));
        response.put("result", I18nUtils.resolveMessage("FAILED", "Failed"));
        error.put("code", code);
        error.put("message", message);
        error.put("path", path);
        response.put("error", error);
        ContextUtil.getTraceContext().ifPresent(v -> response.put("trace_id", v.getTraceId()));
        if ((exception instanceof Exception) && (ContextUtil.isProfile("dev", "local", "test", "debug")))
            description = ((Exception) exception).getLocalizedMessage();
        error.put("description", ObjectUtils.isEmpty(description) ? message : description);
        return response;
    }
}