package com.rui.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.rui.Entity.Student;
import feign.hystrix.FallbackFactory;

@Component
public class StudentClientFallbackFactory implements FallbackFactory<StudentClientService> {

	public StudentClientService create(Throwable cause) {
		return new StudentClientService() {

			public Student get(Integer id) {
				return null;
			}

			public List<Student> list() {
				List<Student> list = new ArrayList<Student>();
				Student s = new Student();
				s.setId(1);
				s.setGrade("FallbackFactory");
				s.setName("list-timeout");
				list.add(s);
				return list;
			}

			public boolean save(Student student) {
				return false;
			}

			public boolean delete(Integer id) {
				return false;
			}

			public Map<String, Object> getInfo() {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("code", 500);
				map.put("info", "error!");
				return map;
			}
		};
	}

}
