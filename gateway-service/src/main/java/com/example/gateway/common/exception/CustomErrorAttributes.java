package com.example.gateway.common.exception;

import com.example.gateway.common.BizException;
import com.example.gateway.common.ContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class CustomErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, options);
        Object exception = webRequest.getAttribute(ErrorAttributes.ERROR_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);
        int status = (int) map.get("status");
        String message = (String) map.get("message");
        String description = (String) map.get("description");
        String er = (String) map.get("error");
        message = StringUtils.isEmpty(message) ? er : message;
        String code = "E0555";
        if (HttpStatus.NOT_FOUND.value() == status) {
            message = "API not found";
            code = "E0404";
            log.error(message);
        } else if (exception instanceof BizException) {
            code = ((BizException) exception).getError().getValue();
            if (exception instanceof Exception) {
                message = ((Exception) exception).getMessage();
                description = ((Exception) exception).getLocalizedMessage();
            }
        } else if (HttpStatus.UNAUTHORIZED.value() == status) {
            code = "E0401";
            if (StringUtils.isEmpty(message))
                message = "Unauthorized access";
        }
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> error = new HashMap<>();
        response.put("status", String.valueOf(status));
        response.put("result", "Failed");
        error.put("code", code);
        error.put("message", message);
        error.put("description", ObjectUtils.isEmpty(description) ? message : description);
        response.put("error", error);
        ContextUtil.getTraceContext().ifPresent(v -> response.put("trace_id", v.getTraceId()));
//        if (exception instanceof Exception)
//            log.error(((Exception) exception).getMessage(), exception);
        return response;
    }
}