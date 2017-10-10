package gds.com.springhello.demo.controller;

import gds.com.springhello.demo.dto.EmployeeDTO;
import gds.com.springhello.demo.service.TestService;
import gds.com.weixin.util.WeiXinUtils;
import net.sf.json.JSONArray;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * <p>Description:查询人员信息 </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company:   刘云鹏<br></p>
 * @date 创建时间：4 Sep 2017 11:52:36
 * @author liuyunpeng
 * @version  1.0
 */
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
		
		//当第一次查询时，设置显示行数
		if(!map.containsKey("showDataLineCount")) {
			map.put("showDataLineCount", "0");
		}
		
		//获取人员信息
	    List<EmployeeDTO>  employeeList = this.testServiceImpl.findEmployeeByParam(map); 
	    //设置数据显示行数
	    map.put("showDataLineCount", employeeList.size()+"");
		model.addAttribute("employeeList", employeeList);
		model.addAttribute("map", map);
		
		return "/srpinghello.demo/findSrpingHello";
	}
	
	/**
	 *   使用Ajax 查询当前数据
	 * @param request
	 * @param response
	 * @param model
	 * @param map
	 * @throws IOException 
	 */
	@RequestMapping("/findAjaxSpringHello")
	public void    findAjaxSpringHello(HttpServletRequest request, HttpServletResponse response,Model  model, @RequestParam Map<String,String>map) throws IOException {
		//获取人员信息
	    List<EmployeeDTO>  employeeList = this.testServiceImpl.findEmployeeByParam(map); 
	     
	    WeiXinUtils.setResponseContent(response);
		response.getWriter().write(JSONArray.fromObject(employeeList).toString());
		
	}
	
}
