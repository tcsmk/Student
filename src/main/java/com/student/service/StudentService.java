package com.student.service;

import com.student.modal.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    public Student createStudent(Student student);

    public List<Student> getAllStudentDetails();

    public Optional<Student> getStudentDetailsById(long sno);

    public Student setPassOrFailToStudents(Student student);

    public void deleteStudentDetails(Long sno);

    public void writeCSVFile();




}

