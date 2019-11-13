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
import org.springframework.web.client.RestTemplate;
import com.rui.Entity.Student;
/**
 * use RestTemplate(@Resource)
 *
 */
@RestController
@RequestMapping("/student")
public class StudentConsumerController {
	@Resource
	private RestTemplate restTemplate;

	private final static String PRE_HOST = "http://eureka-provider";

	/**
	 * for hystrix
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/getInfo")
	@ResponseBody
	public Map<String, Object> getInfo() {
		return restTemplate.getForObject(PRE_HOST + "/student/getInfo/", Map.class);
	}

	@PostMapping(value = "/save")
	public boolean save(Student s) {
		return restTemplate.postForObject(PRE_HOST + "/student/save", s, Boolean.class);
	}

	@SuppressWarnings("unchecked")
	@GetMapping(value = "/list")
	@ResponseBody
	public List<Student> list() {
		return restTemplate.getForObject(PRE_HOST + "/student/list", List.class);
	}

	@GetMapping(value = "/get/{id}")
	public Student get(@PathVariable("id") Integer id) {
		return restTemplate.getForObject(PRE_HOST + "/student/get/" + id, Student.class);
	}

	@GetMapping(value = "/delete/{id}")
	public boolean delete(@PathVariable("id") Integer id) {
		return restTemplate.getForObject(PRE_HOST + "/student/delete/" + id, Boolean.class);
	}
}
