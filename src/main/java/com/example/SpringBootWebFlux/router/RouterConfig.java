package com.example.SpringBootWebFlux.router;

import com.example.SpringBootWebFlux.handler.StudentHandler;
import com.example.SpringBootWebFlux.handler.StudentStreamHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@AllArgsConstructor
public class RouterConfig {

    private StudentHandler studentHandler;
    private StudentStreamHandler studentStreamHandler;

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions.route()
                .GET("/router/students", studentHandler::loadStudents)
                .GET("/router/students/stream", studentStreamHandler::loadStudents)
                .GET("/router/students/{studentId}", studentHandler::findStudent)
                .POST("/router/student/save", studentHandler::saveStudent)
                .build();
    }
}
