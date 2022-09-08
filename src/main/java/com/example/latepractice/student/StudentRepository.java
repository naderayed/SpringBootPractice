package com.example.latepractice.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query ("SELECT s from Student s where s.studentEmail =?1")
    Optional<Student> findByStudentEmail(String email);
}
