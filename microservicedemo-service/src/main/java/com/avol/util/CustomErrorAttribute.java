package com.avol.util;

import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.web.context.request.RequestAttributes;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Durga on 6/15/2015.
 */
public class CustomErrorAttribute implements ErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = new LinkedHashMap<String, Object>();
        errorAttributes.put("timestamp", new Date());
        return errorAttributes;
    }

    @Override
    public Throwable getError(RequestAttributes requestAttributes) {
        return new IllegalStateException("Illegal State of the request.");
    }
}
