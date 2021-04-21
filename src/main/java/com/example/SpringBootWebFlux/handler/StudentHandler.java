package com.example.SpringBootWebFlux.handler;

import com.example.SpringBootWebFlux.entity.Student;
import com.example.SpringBootWebFlux.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class StudentHandler {

    private StudentRepository studentRepository;

    public Mono<ServerResponse> loadStudents(ServerRequest serverRequest) {
        Flux<Student> studentList = studentRepository.getStudentList();
        return ServerResponse.ok().body(studentList, Student.class);
    }

    public Mono<ServerResponse> findStudent(ServerRequest serverRequest) {
        int studentId = Integer.valueOf(serverRequest.pathVariable("studentId"));
        Mono<Student> studentFindById = studentRepository.getStudentList()
                .filter(student -> student.getId() == studentId).next();
        return ServerResponse.ok().body(studentFindById, Student.class);
    }

    public Mono<ServerResponse> saveStudent(ServerRequest serverRequest) {
        Mono<Student> studentMono = serverRequest.bodyToMono(Student.class);
        Mono<String> saveResponse = studentMono.map(student -> String.format("% : %", student.getId(), student.getName()));
        return ServerResponse.ok().body(saveResponse, String.class);
    }
}
