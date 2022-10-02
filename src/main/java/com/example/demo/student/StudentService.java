package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail((student.getEmail()));
        if(studentOptional.isPresent()) {
            throw new IllegalStateException("Email Taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(int studentId) {
        boolean exits = studentRepository.existsById(studentId);
        if (!exits) {
            throw new IllegalStateException("Student with ID " + studentId + " does not exits");
        } else {
            studentRepository.deleteById(studentId);
        }
    }
    @Transactional
    public void updateStudent(int studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student with ID "+ studentId +" does not exist"));
        if(name != null)
        {
            student.setName(name);
            System.out.println("Name updated "+name);
        }
        if(email != null) {
            student.setEmail(email);
            System.out.println("Email Updated "+email);
        }
    }
}
