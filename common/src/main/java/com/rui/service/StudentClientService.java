package com.rui.service;

import java.util.List;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.rui.Entity.Student;
/**
 * for @EnableFeignClients
 *
 */
@FeignClient(value="SPRINGCLOUD-STUDENT",fallbackFactory=StudentClientFallbackFactory.class)//fallbackFactory for hystrix&feign
public interface StudentClientService {
 
    @GetMapping(value="/student/get/{id}")
    Student get(@PathVariable("id") Integer id);
    
    @GetMapping(value="/student/list")
    List<Student> list();
     
    @PostMapping(value="/student/save")
    boolean save(Student student);
     
    @GetMapping(value="/student/delete/{id}")
    boolean delete(@PathVariable("id") Integer id);
    
    @GetMapping(value="/student/getInfo")
    Map<String,Object>getInfo();
}
