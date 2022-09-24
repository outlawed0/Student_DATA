package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student tobi = new Student(
                    "Tobi",
                    "tobi@gmail.com",
                    LocalDate.of(2000, Month.JANUARY, 1)
            );
            Student alex = new Student(
                    "Alex",
                    "alex@gmail.com",
                    LocalDate.of(2004, Month.AUGUST, 17)
            );
            Student vibhor = new Student(
                    "Vibhor",
                    "v@gmail.com",
                    LocalDate.of(2010, Month.JANUARY, 15)
            );

            repository.saveAll(
                    List.of(tobi, alex, vibhor)
            );
        };
    }
}
