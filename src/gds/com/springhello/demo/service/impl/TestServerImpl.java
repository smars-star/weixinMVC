package gds.com.springhello.demo.service.impl;

import gds.com.springhello.demo.dao.TestDao;
import gds.com.springhello.demo.dto.EmployeeDTO;
import gds.com.springhello.demo.service.TestService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServerImpl implements TestService {
       
	@Autowired
	 TestDao  testDaoImpl;

	@Override
	public EmployeeDTO findEmployee() {
		return this.testDaoImpl.findEmployee();
	}


	@Override
	public List<EmployeeDTO> findEmployeeByParam(Map<String, String> map) {
		return this.testDaoImpl.findEmployeeByParam(map);
	}

	
}
