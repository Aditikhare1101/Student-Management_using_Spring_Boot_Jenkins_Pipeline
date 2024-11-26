package net.javaguides.sms.service;

import net.javaguides.sms.entity.Student;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public interface StudentService {
	Student registerStudent(Student student);
	Student register(@RequestBody Student student);
	boolean login(String email, String password);
	Student updateStudent(Long id, Student updatedStudent);
	Optional<Student> getStudentById(Long id);
}
