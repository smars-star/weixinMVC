package gds.com.springhello.demo.controller;

import gds.com.springhello.demo.dto.EmployeeDTO;
import gds.com.springhello.demo.service.TestService;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/test")
@Controller
public class TestController {
    
	/**
	 *   注入service
	 */
	@Autowired
	 TestService  testServiceImpl;
	
	
	@RequestMapping("/findSrpingHello")
	public String  findSrpingHello(HttpServletRequest request, HttpServletResponse response,Model  model, @RequestParam Map<String,String>map){
		
		//获取人员信息
	    List<EmployeeDTO>  employeeList = this.testServiceImpl.findEmployeeByParam(map); 
	    
		model.addAttribute("employeeList", employeeList);
		model.addAttribute("map", map);
		
		return "/srpinghello.demo/findSrpingHello";
	}
	
}
