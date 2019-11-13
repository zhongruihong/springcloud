package com.rui.student.controller;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rui.Entity.Student;
import com.rui.service.StudentClientService;

/**
 * use StudentClientService which @FeignClient
 */
@RestController
@RequestMapping("/student")
public class StudentConsumerController {
	@Resource
	private StudentClientService studentClientService;

	@PostMapping(value = "/save")
	public boolean save(Student s) {
		return studentClientService.save(s);
	}

	@GetMapping(value = "/list")
	public List<Student> list() {
		return studentClientService.list();
	}

	@GetMapping(value = "/get/{id}")
	public Student get(@PathVariable("id") Integer id) {
		return studentClientService.get(id);
	}

	@GetMapping(value = "/delete/{id}")
	public boolean delete(@PathVariable("id") Integer id) {
		return studentClientService.delete(id);
	}

	@GetMapping(value = "/getInfo")
	@ResponseBody
	public Map<String, Object> getInfo() {
		return studentClientService.getInfo();
	}
}
