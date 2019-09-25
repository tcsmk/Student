package com.student.controllers;

import com.student.modal.Student;
import com.student.utils.TestUtils;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldCreateNewStudentWithStatusFail() throws Exception {
        Student student = getStudent();
        mockMvc.perform(post("/student/create").content(TestUtils.convertObjectToJson(student))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Is.is("F")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sname", Is.is("tammu")));

    }

    @Test
    public void shouldCreateNewStudentWithStatusPass() throws Exception {
        Student student = getStudent();
        mockMvc.perform(post("/student/create").content(TestUtils.convertObjectToJson(student))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Is.is("P")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sname", Is.is("tammu")));

    }

    private Student getStudent() {
        Student student = new Student();
        student.setSname("tammu");
        student.setEnglish(76);
        student.setMaths(67);
        student.setScience(65);
        return student;
    }

    @Test
    public void shouldGetStudentById() throws Exception {
        Student student = getStudent();
        MvcResult result = mockMvc.perform(post("/student/create").content(TestUtils.convertObjectToJson(student))
                .contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn();
        Student studentOne = TestUtils.convertJsonToObject(result.getResponse().getContentAsString());
        mockMvc.perform(get("/student/getStudent/" + studentOne.getSno())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Is.is("P")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sname", Is.is("tammu")));
    }

    @Test
    public void deleteStudentDetails() throws Exception {
        Student student = getStudent();
        MvcResult result = mockMvc.perform(post("/student/create").content(TestUtils.convertObjectToJson(student))
                .contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn();
        Student studentOne = TestUtils.convertJsonToObject(result.getResponse().getContentAsString());


        mockMvc.perform(delete("/student/delete/" + studentOne.getSno())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());


        mockMvc.perform(get("/student/getStudent/" + studentOne.getSno())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());




    }

}


