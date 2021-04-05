package com.example.SpringBootWebFlux.service;

import com.example.SpringBootWebFlux.entity.Student;
import com.example.SpringBootWebFlux.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    private StudentRepository studentRepository;

    public List<Student> loadAllStudents() {
        long startCurrentTimeMillis = System.currentTimeMillis();
        List<Student> studentList = studentRepository.getStudents();
        long endCurrentTimeMillis = System.currentTimeMillis();
        System.out.println(String.format("Total execution Time : %s", (endCurrentTimeMillis - startCurrentTimeMillis)));
        return studentList;
    }

    public Flux<Student> loadAllStudentsStream() {
        long startCurrentTimeMillis = System.currentTimeMillis();
        Flux<Student> studentList = studentRepository.getStudentsStream();
        long endCurrentTimeMillis = System.currentTimeMillis();
        System.out.println(String.format("Total execution Time : %s", (endCurrentTimeMillis - startCurrentTimeMillis)));
        return studentList;
    }
}