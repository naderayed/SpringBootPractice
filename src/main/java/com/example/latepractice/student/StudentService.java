package com.example.latepractice.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getAllStudentsService() {
        return studentRepository.findAll();
    }

    public Student getStudentService(long studentId) {

        return studentRepository.
                findById(studentId).
                orElseThrow(() -> new IllegalStateException("Student tNot Found Exception"));
    }

    public void registerStudentService(Student student)  throws IllegalStateException{
        Optional<Student> StudentByEmail = studentRepository.findByStudentEmail(student.getStudentEmail());
        if (StudentByEmail.isEmpty()){
        studentRepository.save(student);
        }
        else
        {throw new IllegalStateException("student already exist");}
    }
    @Transactional
    public void updateStudentService(long studentId, String studentAddress, Integer studentAge, String studentEmail, String studentName) {

        Student studentFound = studentRepository
                .findById(studentId)
                .orElseThrow(() -> new IllegalStateException("student not found"));

        if (studentAddress != null && studentAddress.length()>0) {
            studentFound.setStudentAddress(studentAddress);
        }

        if (studentName != null && studentName.length()>0) {
            studentFound.setStudentName(studentName);

        }
        if (studentAge != null && studentAge>0) {
            studentFound.setStudentAge(studentAge);
        }

        if (studentEmail != null && studentEmail.length()>0){
            studentFound.setStudentEmail(studentEmail);
        }



    }

    public void deleteStudentService(long studentId) {
        Optional<Student> byId = studentRepository.findById(studentId);
        if (byId.isPresent())
            studentRepository.deleteById(studentId);

    }
}
