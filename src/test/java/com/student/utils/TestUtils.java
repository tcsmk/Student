package com.student.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.modal.Student;

import java.io.IOException;

public class TestUtils {

    static ObjectMapper objectMapper = new ObjectMapper();

    public static String convertObjectToJson(Student student) {
        try {
            return objectMapper.writeValueAsString(student);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Student convertJsonToObject(String jsonString) throws IOException {
        return objectMapper.readValue(jsonString,Student.class);

    }
}
