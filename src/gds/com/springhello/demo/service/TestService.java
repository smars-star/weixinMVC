package gds.com.springhello.demo.service;

import gds.com.springhello.demo.dto.EmployeeDTO;

import java.util.List;
import java.util.Map;

public interface TestService {

	/**
	 *  find Employee Info 
	 * @param map
	 * @return
	  */
	List<EmployeeDTO> findEmployeeByParam(Map<String, String> map);

	
	
	EmployeeDTO findEmployee();

}
