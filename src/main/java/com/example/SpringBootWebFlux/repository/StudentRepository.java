package com.example.SpringBootWebFlux.repository;

import com.example.SpringBootWebFlux.entity.Student;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class StudentRepository {
    public List<Student> getStudents() {
        return IntStream.rangeClosed(1, 10)
                .peek(StudentRepository::sleepExecution)
                .peek(i -> System.out.println(String.format("Processing Count : %s", i)))
                .mapToObj(i -> new Student(i, String.format("Customer%s", i)))
                .collect(Collectors.toList());
    }

    public Flux<Student> getStudentsStream() {
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println(String.format("Processing Count : %s", i)))
                .map(i -> new Student(i, String.format("Customer%s", i)));
    }

    private static void sleepExecution(int i) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
