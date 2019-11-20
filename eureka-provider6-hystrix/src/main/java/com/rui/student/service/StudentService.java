package com.rui.student.service;

import java.util.List;
import java.util.Map;
import com.rui.Entity.Student;

public interface StudentService {

	void save(Student s);

	Student findById(Integer id);

	List<Student> list();

	void delete(Integer id);
	/**
	 * for hystrix&feign test: common-StudentClientFallbackFactory\StudentClientService
	 * @return
	 */
	Map<String, Object> getInfo();
}
