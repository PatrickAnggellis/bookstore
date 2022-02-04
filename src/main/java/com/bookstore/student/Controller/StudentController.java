package com.bookstore.student.Controller;

import com.bookstore.student.Exceptions.StudentNotFoundException;
import com.bookstore.student.entity.Student;
import com.bookstore.student.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/students")
    List<Student> allStudents(){
        return studentRepository.findAll();
    }

    @PostMapping("/students")
    Student newStudent(@RequestBody Student newStudent) {
        return studentRepository.save(newStudent);
    }

    @GetMapping("/student/{id}")
    Student findById(@PathVariable Long id) {
        return studentRepository.findById(id)
                .orElseThrow(()->new StudentNotFoundException(id));
    }

    @PutMapping("/student/{id}")
    Student replaceStudent(@PathVariable Long id, @RequestBody Student newStudent) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setName(newStudent.getName());
                    student.setAge(newStudent.getAge());
                    return studentRepository.save(student);
                })
                .orElseGet(()-> {
                    newStudent.setId(id);
                    return studentRepository.save(newStudent);
                });
    }

    @DeleteMapping("/student/{id}")
    void deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
    }

}

