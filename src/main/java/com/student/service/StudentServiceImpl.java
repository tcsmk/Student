package com.student.service;


import com.student.modal.Student;
import com.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ParseLong;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.transaction.Transactional;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional()
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;


    @Override
    public Student createStudent(Student student) {
        student.setTotal(student.getEnglish() + student.getMaths() + student.getScience());
        student.setAverage((student.getTotal()) / 3.0);
        Student studentOne = setPassOrFailToStudents(student);
        student = studentRepository.save(studentOne);
        return student;
    }

    @Override
    public List<Student> getAllStudentDetails() {
        return studentRepository.findAll();

    }

    @Override
    public Optional<Student> getStudentDetailsById(long sno) {
        return studentRepository.findById(sno);
    }

    @Override
    public Student setPassOrFailToStudents(Student student) {
        if (student.getEnglish() < 40 || student.getMaths() < 40 || student.getScience() < 40) {
            student.setStatus("F");
        } else {
            student.setStatus("P");
        }
        return student;
    }

    @Override
    public void deleteStudentDetails(Long sno) {
        studentRepository.deleteById(sno);
    }

    @Override
    public void writeCSVFile() {
        List<Student> student = getAllStudentDetails();
        ICsvBeanWriter beanWriter = null;
        CellProcessor[] processors = new CellProcessor[]{
                new ParseLong(),
                new ParseInt(),
                new ParseInt(),
                new ParseInt(),
                new ParseInt(),
                new ParseDouble()
        };

        try {
            beanWriter = new CsvBeanWriter(new FileWriter("/Users/sandhya/student.csv"),
                    CsvPreference.STANDARD_PREFERENCE);
            String[] header = {"sno", "sname", "english", "maths", "science", "total", "average"};
            beanWriter.writeHeader(header);

            for (Student studentOne : student) {
                beanWriter.write(studentOne, header, processors);
            }

        } catch (IOException ex) {
            System.err.println("Error writing the CSV file: " + ex);
        } finally {
            if (beanWriter != null) {
                try {
                    beanWriter.close();
                } catch (IOException ex) {
                    System.err.println("Error closing the writer: " + ex);
                }
            }
        }
    }
}

