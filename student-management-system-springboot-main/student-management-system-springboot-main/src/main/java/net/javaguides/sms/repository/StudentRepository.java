package net.javaguides.sms.repository;


import net.javaguides.sms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
}


//
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import net.javaguides.sms.entity.Student;
//
//public interface StudentRepository extends JpaRepository<Student, Long>{
//
//}
