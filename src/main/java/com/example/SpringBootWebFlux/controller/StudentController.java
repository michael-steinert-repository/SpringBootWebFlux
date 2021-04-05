package com.example.SpringBootWebFlux.controller;

import com.example.SpringBootWebFlux.entity.Student;
import com.example.SpringBootWebFlux.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.loadAllStudents();
    }

    /* Asynchronous and Non Blocking */
    /* The Data are supplied directly and not only after the End of the Method (as in the synchronous Approach) */
    /* If the Subscriber cancel the Request the Computation also will canceled */
    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Student> getAllStudentsStream() {
        return studentService.loadAllStudentsStream();
    }
}