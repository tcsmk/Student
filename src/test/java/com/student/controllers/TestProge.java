package com.student.controllers;

import com.student.modal.Student;

import java.util.Optional;

public class TestProge {

    public static void main(String[] args) {
        Optional<Student> student =  Optional.empty();

        System.out.println(student.isPresent());
    }
}
