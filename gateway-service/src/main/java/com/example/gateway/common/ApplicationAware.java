package com.example.gateway.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.ClassUtils;

import java.util.Optional;

@Slf4j
public class ApplicationAware implements ApplicationContextAware, ApplicationReadyListener {


    private static Object sleuthTracer;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        log.debug("initial application context to ContextUtil");
        ContextUtil.setContext(context);
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        loadSleuthTracer();
    }

    private void loadSleuthTracer() {
        String traceClass = "org.springframework.cloud.sleuth.Tracer";
        if (ClassUtils.isPresent(traceClass, null)) {
            log.debug("find sleuth tracer:{}", traceClass);
            try {
                Class<?> _class = Class.forName(traceClass);
                Optional<?> optBean = ContextUtil.optBean(_class);
                optBean.ifPresent(v -> sleuthTracer = v);
            } catch (Exception e) {
                log.warn("Cannot load org.springframework.cloud.sleuth.Tracer", e);
            }
        }
    }

    public static Optional<Object> getSleuthTracer() {
        return Optional.ofNullable(sleuthTracer);
    }
}
