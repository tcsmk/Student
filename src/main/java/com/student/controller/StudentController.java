package com.student.controller;

import com.student.exceptions.StudentNotFoundException;
import com.student.modal.Student;
import com.student.repository.StudentRepository;
import com.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/create")
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("/allstudents")
    public List<Student> getAllStudentDetails() {
        return studentService.getAllStudentDetails();
    }

    @GetMapping("/getStudent/{Id}")
    public Student getStudentById(@PathVariable(value = "Id") long sno) {
        Optional<Student> student = studentService.getStudentDetailsById(sno);
        if (student.isPresent()) {
            return student.get();
        }
        return null;
    }

    @DeleteMapping("/delete/{Id}")
    public void deleteStudentById(@PathVariable(value = "Id") long sno) {
        studentService.deleteStudentDetails(sno);


    }
}


