package com.rui.student.service.impl;

import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.rui.Entity.Student;
import com.rui.com.rui.student.repository.StudentRepository;
import com.rui.student.service.StudentService;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

	@Resource
	private StudentRepository studentRepository;

	public void save(Student s) {
		studentRepository.save(s);
	}

	public Student findById(Integer id) {
		Optional<Student> optional = studentRepository.findById(id);
		Student student = optional.get();
		return student;
	}

	public List<Student> list() {
		List<Student> list = studentRepository.findAll();
		return list;
	}

	public void delete(Integer id) {
		studentRepository.deleteById(id);
	}
}
