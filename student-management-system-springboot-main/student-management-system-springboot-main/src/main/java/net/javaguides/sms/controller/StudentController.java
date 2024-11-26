package net.javaguides.sms.controller;

import net.javaguides.sms.entity.LoginRequest;
import net.javaguides.sms.entity.Student;
import net.javaguides.sms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping("/register")
	public ResponseEntity<Student> register(@RequestBody Student student) {
		// Validate and register the student
		Student registeredStudent = studentService.register(student);

		if (registeredStudent != null) {
			return ResponseEntity.ok(registeredStudent);  // Return registered student details
		} else {
			return ResponseEntity.status(400).body(null);  // Return bad request if registration fails
		}
	}


	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
		String email = loginRequest.getEmail();
		String password = loginRequest.getPassword();

		// Call the login method from the service
		if (studentService.login(email, password)) {
			return ResponseEntity.ok("Login successful!");
		} else {
			return ResponseEntity.status(400).body("Invalid email or password!");
		}
	}

	@PutMapping("/{id}")
	public Student updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
		return studentService.updateStudent(id, updatedStudent);
	}

	@GetMapping("/{id}")
	public Optional<Student> getStudent(@PathVariable Long id) {
		return studentService.getStudentById(id);
	}

	@GetMapping("/test")
	public String test() {
		return "Controller is working!";
	}

}

//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import net.javaguides.sms.entity.Student;
//import net.javaguides.sms.service.StudentService;
//
//@Controller
//public class StudentController {
//
//	private StudentService studentService;
//
//	public StudentController(StudentService studentService) {
//		super();
//		this.studentService = studentService;
//	}
//
//	// handler method to handle list students and return mode and view
//	@GetMapping("/students")
//	public String listStudents(Model model) {
//		model.addAttribute("students", studentService.getAllStudents());
//		return "students";
//	}
//
//	@GetMapping("/students/new")
//	public String createStudentForm(Model model) {
//
//		// create student object to hold student form data
//		Student student = new Student();
//		model.addAttribute("student", student);
//		return "create_student";
//
//	}
//
//	@PostMapping("/students")
//	public String saveStudent(@ModelAttribute("student") Student student) {
//		studentService.saveStudent(student);
//		return "redirect:/students";
//	}
//
//	@GetMapping("/students/edit/{id}")
//	public String editStudentForm(@PathVariable Long id, Model model) {
//		model.addAttribute("student", studentService.getStudentById(id));
//		return "edit_student";
//	}
//
//	@PostMapping("/students/{id}")
//	public String updateStudent(@PathVariable Long id,
//			@ModelAttribute("student") Student student,
//			Model model) {
//
//		// get student from database by id
//		Student existingStudent = studentService.getStudentById(id);
//		existingStudent.setId(id);
//		existingStudent.setFirstName(student.getFirstName());
//		existingStudent.setLastName(student.getLastName());
//		existingStudent.setEmail(student.getEmail());
//
//		// save updated student object
//		studentService.updateStudent(existingStudent);
//		return "redirect:/students";
//	}
//
//	// handler method to handle delete student request
//
//	@GetMapping("/students/{id}")
//	public String deleteStudent(@PathVariable Long id) {
//		studentService.deleteStudentById(id);
//		return "redirect:/students";
//	}
//
//}
