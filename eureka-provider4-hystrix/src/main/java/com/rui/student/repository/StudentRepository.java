package com.rui.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rui.Entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
