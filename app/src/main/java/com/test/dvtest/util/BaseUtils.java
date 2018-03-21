package com.test.dvtest.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class BaseUtils {

    private static ObjectMapper mapper;

    static {

        mapper = new ObjectMapper();

        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        DateFormat WS_DATE_FORMAT = new SimpleDateFormat("MMM dd, yyyy h:mm:ss aa", Locale.ENGLISH);

        mapper.setDateFormat(WS_DATE_FORMAT);

    }

    public static ObjectMapper getMapper() {

        return mapper;
    }

    public static String getObjectAsJson(Object theObject) {

        String jsonString;

        try {

            jsonString = getMapper().writeValueAsString(theObject);
        } catch (JsonProcessingException e) {
            jsonString = "";
        }

        return jsonString;

    }

}
