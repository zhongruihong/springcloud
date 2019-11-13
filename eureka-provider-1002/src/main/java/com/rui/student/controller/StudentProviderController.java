package com.rui.student.controller;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rui.Entity.Student;
import com.rui.student.service.StudentService;

@RestController // @RestController=@Controller+@ResponseBody json
@RequestMapping("/student")
public class StudentProviderController {
	// controller
	@Resource
	private StudentService studentService;

	@PostMapping(value = "/save")
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
	public List<Student> list() {
		return studentService.list();
	}

	@GetMapping(value = "/get/{id}") // restful
	public Student get(@PathVariable("id") Integer id) {
		return studentService.findById(id);
	}

	@GetMapping(value = "/delete/{id}")
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
