package com.example.latepractice.student;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;
    @Column
    private String studentName;

    private String studentEmail;
    @Column
    private String studentAddress;
    @Column
    private Integer studentAge;

    public Student() {
    }

    public Student(String studentName, String studentEmail, String studentAddress, Integer studentAge) {
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentAddress = studentAddress;
        this.studentAge = studentAge;
    }

    public Student(Long studentID, String studentName, String studentEmail, String studentAddress, Integer studentAge) {
        this.studentId = studentID;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentAddress = studentAddress;
        this.studentAge = studentAge;
    }



    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public Integer getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(Integer studentAge) {
        this.studentAge = studentAge;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentId.equals(student.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studentAddress='" + studentAddress + '\'' +
                ", studentAge=" + studentAge + '\'' +
                ", studentEmail=" +studentEmail +
                '}';
    }


}

