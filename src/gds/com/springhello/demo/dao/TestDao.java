package gds.com.springhello.demo.dao;

import gds.com.springhello.demo.dto.EmployeeDTO;

import java.util.List;
import java.util.Map;

public interface TestDao {

	
	List<EmployeeDTO> findEmployeeByParam(Map<String, String> map);
    
	
	EmployeeDTO findEmployee();

}
