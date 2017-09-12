package gds.com.springhello.demo.dao.impl;

import gds.com.common.base.BaseDao;
import gds.com.springhello.demo.dao.TestDao;
import gds.com.springhello.demo.dto.EmployeeDTO;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class TestDaoImpl  extends BaseDao implements TestDao{

	
	@Override
	public List<EmployeeDTO> findEmployeeByParam(Map<String, String> map) {
		return this.sqlSessionTemplate.selectList("testDaoMapper.findEmployeeByParam",map);
	}

	@Override
	public EmployeeDTO findEmployee() {
		return (EmployeeDTO)this.sqlSessionTemplate.selectOne("testDaoMapper.findEmployee");
	}

}
