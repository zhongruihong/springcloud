package com.rui.student.controller;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.rui.Entity.Student;
import com.rui.student.service.StudentService;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
/**
 * 
 * but if there is no @HystrixCommand, the request connot be monitored by dashboard.
 * 
 */
@RestController
@RequestMapping("/student")
public class StudentProviderController {

	@Resource
	private StudentService studentService;

	@ResponseBody
    @GetMapping(value="/getInfo")
	@HystrixCommand
    public Map<String,Object> getInfo() throws InterruptedException{
        Thread.sleep(1000);
        return studentService.getInfo();
    }
	
	@PostMapping(value = "/save")
	@HystrixCommand
	public boolean save(@RequestBody Student s) {
		try {
			studentService.save(s);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@GetMapping(value = "/list")
	@HystrixCommand
	public List<Student> list() throws InterruptedException {
		//Thread.sleep(1000);
		return studentService.list();
	}

	@GetMapping(value = "/get/{id}") // restful
	@HystrixCommand
	public Student get(@PathVariable("id") Integer id) {
		return studentService.findById(id);
	}

	@GetMapping(value = "/delete/{id}")
	@HystrixCommand
	public boolean delete(@PathVariable("id") Integer id) {
		try {
			studentService.delete(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
