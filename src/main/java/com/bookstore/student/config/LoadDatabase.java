package com.bookstore.student.config;

import com.bookstore.student.entity.Student;
import com.bookstore.student.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDataBase(StudentRepository repository) {
        return args -> {
            logger.info("Preloding" + repository.save(new Student("Patrick", 30)));
            logger.info("Preloding" + repository.save(new Student("Paulo", 40)));
        };
    }
}
