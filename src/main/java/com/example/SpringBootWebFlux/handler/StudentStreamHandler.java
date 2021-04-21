package com.example.SpringBootWebFlux.handler;

import com.example.SpringBootWebFlux.entity.Student;
import com.example.SpringBootWebFlux.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class StudentStreamHandler {

    private StudentRepository studentRepository;

    public Mono<ServerResponse> loadStudents(ServerRequest serverRequest) {
        Flux<Student> studentList = studentRepository.getStudentsStream();
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(studentList, Student.class);
    }
}
