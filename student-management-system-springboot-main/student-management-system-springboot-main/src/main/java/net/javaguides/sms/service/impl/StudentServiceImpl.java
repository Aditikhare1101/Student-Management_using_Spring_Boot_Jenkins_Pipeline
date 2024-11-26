package net.javaguides.sms.service.impl;

import net.javaguides.sms.entity.Student;
import net.javaguides.sms.repository.StudentRepository;
import net.javaguides.sms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student registerStudent(Student student) {
		if (studentRepository.findByEmail(student.getEmail()).isPresent()) {
			throw new IllegalArgumentException("Email is already in use.");
		}
		return studentRepository.save(student);
	}

	public Student register(Student student) {
		return studentRepository.save(student); // Save the student to the database
	}

	@Override
	public boolean login(String email, String password) {
		Optional<Student> studentOptional = studentRepository.findByEmail(email); // Returns Optional<Student>

		// Check if the student exists and the password matches
		if (studentOptional.isPresent()) {
			Student student = studentOptional.get(); // Get the student object
			return student.getPassword().equals(password); // Validate password
		}
		return false; // If student is not found or password doesn't match
	}

	@Override
	public Student updateStudent(Long id, Student updatedStudent) {
		Student existingStudent = studentRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Student not found"));

		existingStudent.setName(updatedStudent.getName());
		existingStudent.setCourse(updatedStudent.getCourse());
		existingStudent.setAge(updatedStudent.getAge());
		existingStudent.setEmail(updatedStudent.getEmail());
		existingStudent.setPassword(updatedStudent.getPassword());

		return studentRepository.save(existingStudent);
	}

	@Override
	public Optional<Student> getStudentById(Long id) {
		return studentRepository.findById(id);
	}
}


//
//import java.util.List;
//
//import org.springframework.stereotype.Service;
//
//import net.javaguides.sms.entity.Student;
//import net.javaguides.sms.repository.StudentRepository;
//import net.javaguides.sms.service.StudentService;
//
//@Service
//public class StudentServiceImpl implements StudentService{
//
//	private StudentRepository studentRepository;
//
//	public StudentServiceImpl(StudentRepository studentRepository) {
//		super();
//		this.studentRepository = studentRepository;
//	}
//
//	@Override
//	public List<Student> getAllStudents() {
//		return studentRepository.findAll();
//	}
//
//	@Override
//	public Student saveStudent(Student student) {
//		return studentRepository.save(student);
//	}
//
//	@Override
//	public Student getStudentById(Long id) {
//		return studentRepository.findById(id).get();
//	}
//
//	@Override
//	public Student updateStudent(Student student) {
//		return studentRepository.save(student);
//	}
//
//	@Override
//	public void deleteStudentById(Long id) {
//		studentRepository.deleteById(id);
//	}
//
//}
