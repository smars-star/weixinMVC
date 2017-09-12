package gds.com.weixin.controller;

import gds.com.framework.util.AppSettingFactory;
import gds.com.weixin.dto.WeixinDepartmentDTO;
import gds.com.weixin.dto.WeixinEmployeeDTO;
import gds.com.weixin.service.WeixinService;
import gds.com.weixin.util.WeiXinAccessToken;
import gds.com.weixin.util.WeiXinUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 文件名：微信Controller.java<br>
 * 版权：Copyright (c) 2017 刘云鹏<br>
 * 描述：微信测系统，主要包括微信的access_token、管理通讯录、发送微信消息、微信JS-SDK等操作。<br>
 * 修改人：Author: liuyunpeng<br>
 * 版本：Revision: 1.0
 * @param <T>
 */
@RequestMapping("/weixin")
@Controller
public class WeixinController {
	

	/**
	  * 注入weixinService接口
	  */
	@Autowired
	private  WeixinService  weixinServiceImpl;
	
	
	/**
	 *  查询所有的部门信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@SuppressWarnings({ "static-access","unused"})
	@RequestMapping("/findAddressBookEmpInfo.do")
	public  String  findAddressBookEmpInfo(HttpServletRequest request,HttpServletResponse response,Model model){
		try {
			//1.初始化appSetting.properties  配置文件
			AppSettingFactory  appSettingFactory = AppSettingFactory.getInstance();
			
			//2.获取所有部门链接
			List<WeixinDepartmentDTO>  departmentList = this.getWeixinDepList(request);
			
			 List<WeixinEmployeeDTO>  employeeList = null;
			//3 获取部门信息
			String department_id = request.getParameter("department");
			if(!StringUtils.isEmpty(department_id)){//判断当前部门是否为空
				     employeeList = this.getWeixinEmpList(department_id, request);
			}else{
		           //3.1 获取部门下的所有员工信息
				employeeList = this.getWeixinEmpList("1", request);
			}
			
            model.addAttribute("departmentList", new JSONArray().fromObject(departmentList));
            model.addAttribute("employeeList", new JSONArray().fromObject(employeeList));
            model.addAttribute("department", department_id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/weixin/findAddressBookEmpInfo";
	}
	


	/**
	 *   查询部门人员信息
	 * @param request
	 * @param response
	 * @param model
	 * @param map
	 * @throws Exception 
	 */
	@SuppressWarnings({ "static-access"})
	@RequestMapping("/findDepEmployee")
	public  void  findDepEmployee(HttpServletRequest request,HttpServletResponse response,Model model,@RequestParam  Map<String,String>map) throws Exception{
			
		try {
			//获取部门ID
			String department_id = map.get("departmentID");
			
			//获取部门人员信息
			List<WeixinEmployeeDTO>  employeeList  = getWeixinEmpList(department_id,request);
			
            JSONArray jsonArray = new JSONArray().fromObject(employeeList);
			WeiXinUtils.setResponseContent(response);//解决跨域乱码问题
			response.getWriter().println(jsonArray);
		} catch (Exception e) { 
			e.printStackTrace();
		}
		
	}
	

	
	/**
	 *   初始化人员信息
	 * @param request
	 * @param response
	 * @param model
	 * @param userid
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings({ "static-access"})
	@RequestMapping("/modifyInitEmp.do")
	public  String modifyInitEmp(HttpServletRequest request,HttpServletResponse response,Model model,String userid) throws Exception{
		//1. 获部门员工详情链接
		AppSettingFactory  appSettingFactory = AppSettingFactory.getInstance();
		String employee_all_infoUrl  =  appSettingFactory.getAppSetting("employee_get_infoUrl");
		String url = employee_all_infoUrl+WeiXinAccessToken.access_token +"&userid="+userid;
		
		String  weixinEmp = WeiXinUtils.getWeiXinInfo(url, "");
		JSONObject jsonObject = JSONObject.fromObject(weixinEmp);
		WeixinEmployeeDTO  weixinEmpDTO  =  (WeixinEmployeeDTO) jsonObject.toBean(jsonObject, WeixinEmployeeDTO.class);
		
		 //2.获取部门信息
		 List<WeixinDepartmentDTO>  departmentDTOs = this.getWeixinDepList(request);
		 if(departmentDTOs != null && !departmentDTOs.isEmpty()){//判断部门集合是否为空
			 for(WeixinDepartmentDTO weixinDepartmentDTO :departmentDTOs){
				  //如果找到相对应的部门id
				 if(weixinDepartmentDTO.getId() == weixinEmpDTO.getDepartment().get(0)){
					 weixinEmpDTO.setDepName(weixinDepartmentDTO.getName());
				 }
			 }
		 }
		 
		 //3. 获取部门信息
		 JSONArray jsonArray = new JSONArray().fromObject(departmentDTOs);
		
		model.addAttribute("weixinEmpDTO", weixinEmpDTO);
		model.addAttribute("departmentDTOs", jsonArray);
		
		return "/weixin/modifyEmp";
	}

	/**
	 *   更新微信通讯录人员信息
	 * @param request
	 * @param response
	 * @param model
	 * @param weixinEmployeeDTO   人员信息集合
	 */
	@RequestMapping("/modifyEmp.do")
	public  String   modifyEmp(HttpServletRequest request,HttpServletResponse response,Model model,WeixinEmployeeDTO weixinEmployeeDTO){
		String  urlPage = "";//跳转页面
		try {
			
				AppSettingFactory appSettingFactory = AppSettingFactory.getInstance();
				String employee_update_infoUr = appSettingFactory.getAppSetting("employee_update_infoUr");
					
				String url =  employee_update_infoUr + WeiXinAccessToken.access_token;
					
				String updateStr = 	WeiXinUtils.sendHttpWeixinBodyByPost(url,weixinEmployeeDTO,"errmsg");
				  
				if("updated".equals(updateStr)){
				  urlPage =  "/common/doSuccessed";
				  model.addAttribute("SYSTEM_OPERATION_INFO", "更新成功！");
				}else{
					  urlPage =  "/common/showMessage";
				  model.addAttribute("SYSTEM_OPERATION_INFO", "更新失败！errmsg："+updateStr);
				}
		  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return urlPage;

	}
	
	/**
	 *   删除人员信息
	 * @param request
	 * @param response
	 * @param model
	 * @param userid  用户id
	 * @return  
	 * @throws Exception 
	 */
	@RequestMapping("/deleteEmp")
	public String  deleteEmp(HttpServletRequest request,HttpServletResponse response,Model model,String userid) throws Exception{
		String  urlPage = "";
		AppSettingFactory appSettingFactory =AppSettingFactory.getInstance();
		
		String employee_delete_infoUr = appSettingFactory.getAppSetting("employee_delete_infoUr");
		String url = employee_delete_infoUr +WeiXinAccessToken.access_token+"&userid="+userid;
	    
		String deleteStr = WeiXinUtils.sendHttpWeixinBodyByGet(url, "", "errmsg");
		
		if("deleted".equals(deleteStr)){
			  urlPage =  "/common/doSuccessed";
			  model.addAttribute("SYSTEM_OPERATION_INFO", "删除成功！");
	   }else{
			  urlPage =  "/common/showMessage";
			  model.addAttribute("SYSTEM_OPERATION_INFO", "删除失败！errmsg："+deleteStr);
	    }
		return urlPage;
	}
	
	/**
	 *   新增人员信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings({"static-access" })
	@RequestMapping("/addInitEmp")
	 public String addInitEmp(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
		
		 //获取部门信息
		List<WeixinDepartmentDTO>  departmentList = this.getWeixinDepList(request);
		JSONArray  jsonArray = new JSONArray().fromObject(departmentList);
		
		model.addAttribute("departmentList", jsonArray);
		 
		 return "/weixin/addEmp";
		 
	 }
	
	/**
	 *  添加人员信息
	 * @param request
	 * @param response
	 * @param model
	 * @param weixinEmployeeDTO
	 * @return
	 * @throws Exception  
	 */
	@RequestMapping("/addEmp")
	public  String  addEmp(HttpServletRequest request,HttpServletResponse response,Model model,WeixinEmployeeDTO weixinEmployeeDTO) throws Exception{
		
		String urlPage = "";
		
	    AppSettingFactory appSettingFactory =AppSettingFactory.getInstance();
		
		String employee_add_infoUr = appSettingFactory.getAppSetting("employee_add_infoUr");
		String url = employee_add_infoUr +WeiXinAccessToken.access_token;
	    
		String addStr = WeiXinUtils.sendHttpWeixinBodyByPost(url, weixinEmployeeDTO, "errmsg");
		
		if("created".equals(addStr)){
			  urlPage =  "/common/doSuccessed";
			  model.addAttribute("SYSTEM_OPERATION_INFO", "添加成功！");
	    }else{
			  urlPage =  "/common/showMessage";
			  model.addAttribute("SYSTEM_OPERATION_INFO", "添加失败！errmsg："+addStr);
	    }
		
		return urlPage;
	}
	
	
	/**
	 *   初始化添加部门页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/addInitDep.do")
	public String  addInitDep(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
		
		List<WeixinDepartmentDTO>  departmentList = this.getWeixinDepList(request);
		
		model.addAttribute("departmentList", departmentList);
		return "/weixin/addDep";
	}
	
	/**
	 *   添加部门信息
	 * @param request
	 * @param response
	 * @param model
	 * @param weixinDepartmentDTO
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/addDep")
	public String addDep(HttpServletRequest request,HttpServletResponse response,Model model,WeixinDepartmentDTO weixinDepartmentDTO) throws Exception{
	
	    String urlPage = "";
		
	    AppSettingFactory appSettingFactory =AppSettingFactory.getInstance();
		
		String department_add_infoUrl = appSettingFactory.getAppSetting("department_add_infoUrl");
		String url = department_add_infoUrl +WeiXinAccessToken.access_token;
	    
		String addStr = WeiXinUtils.sendHttpWeixinBodyByPost(url, weixinDepartmentDTO, "errmsg");
		
		if("created".equals(addStr)){
			  urlPage =  "/common/doSuccessed";
			  model.addAttribute("SYSTEM_OPERATION_INFO", "添加成功！");
	    }else{
			  urlPage =  "/common/showMessage";
			  model.addAttribute("SYSTEM_OPERATION_INFO", "添加失败！errmsg："+addStr);
	    }
		return urlPage;
		
	}
	
	
	/**
	 *  删除部门信息
	 * @param request
	 * @param response
	 * @param model
	 * @param depID  部门ID
	 * @return
	 * @throws Exception  抛出异常
	 */
	@RequestMapping("/deleteDep")
	public  String  deleteDep(HttpServletRequest request,HttpServletResponse response,Model model,String depID) throws Exception{
		
		String  urlPage = "";
		AppSettingFactory appSettingFactory =AppSettingFactory.getInstance();
		
		String department_delete_infoUrl = appSettingFactory.getAppSetting("department_delete_infoUrl");
		String url = department_delete_infoUrl +WeiXinAccessToken.access_token+"&id="+depID;
	    
		String deleteStr = WeiXinUtils.sendHttpWeixinBodyByGet(url, "", "errmsg");
		
		if("deleted".equals(deleteStr)){
			  urlPage =  "/common/doSuccessed";
			  model.addAttribute("SYSTEM_OPERATION_INFO", "删除成功！");
	   }else{
			  urlPage =  "/common/showMessage";
			  model.addAttribute("SYSTEM_OPERATION_INFO", "删除失败！errmsg："+deleteStr);
	    }
		return urlPage;
	}
	
	/**
	 *   修改部门信息
	 * @param request
	 * @param response
	 * @param model
	 * @param map
	 * @return
	 */
	@RequestMapping("/modifyDep.do")
	public  String  modifyDep(HttpServletRequest request,HttpServletResponse response,Model model,WeixinDepartmentDTO weixinDepartmentDTO){
	
		String  urlPage = "";//跳转页面
		try {
			
				AppSettingFactory appSettingFactory = AppSettingFactory.getInstance();
				String department_update_infoUrl = appSettingFactory.getAppSetting("department_update_infoUrl");
				String url =  department_update_infoUrl + WeiXinAccessToken.access_token;
					
				String updateStr = 	WeiXinUtils.sendHttpWeixinBodyByPost(url,weixinDepartmentDTO,"errmsg");
				  
				if("updated".equals(updateStr)){
				        urlPage =  "/common/doSuccessed";
				        model.addAttribute("SYSTEM_OPERATION_INFO", "更新成功！");
				}else{
					    urlPage =  "/common/showMessage";
				        model.addAttribute("SYSTEM_OPERATION_INFO", "更新失败！errmsg："+updateStr);
				}
		  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return urlPage;
		
	}
	
	
	/**
	 *  对部门list集合排序
	 * @param departmentList
	 * @param request 
	 * @return
	 */
	private   List<WeixinDepartmentDTO>  sortDepList(List<WeixinDepartmentDTO> departmentList, HttpServletRequest request){
		
		 Collections.sort(departmentList, new  Comparator<WeixinDepartmentDTO> (){

			@Override
			public int compare(WeixinDepartmentDTO arg0, WeixinDepartmentDTO arg1) {
				 int hits0 = arg0.getOrder();
                 int hits1 = arg1.getOrder();  
                 if (hits1 > hits0) {  
                     return 1;  
                 } else if (hits1 == hits0) {  
                     return 0;  
                 } else {  
                     return -1;  
                 }  
			}
			 
		 });
	 
		return departmentList;		
	}
	
	/**
	 *   获取部门人员信息
	 * @param department_id  部门ID
	 * @return  
	 * @throws Exception 
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	public List<WeixinEmployeeDTO>  getWeixinEmpList(String department_id,HttpServletRequest request) throws Exception{
		AppSettingFactory  appSettingFactory = AppSettingFactory.getInstance();
		String department_employee_infoUrl = appSettingFactory.getAppSetting("department_employee_allList_infoUrl");

		//获部门员工链接
		String url = department_employee_infoUrl +WeiXinAccessToken.access_token+ "&department_id="+department_id+"&fetch_child=1&status=1";
  
		String   depEmpList = 	WeiXinUtils.getWeiXinInfo(url, "userlist");
		JSONArray  jsonEmpArray = new JSONArray().fromObject(depEmpList);
		List<WeixinEmployeeDTO>  employeeList =   (List<WeixinEmployeeDTO>) jsonEmpArray.toCollection(jsonEmpArray,WeixinEmployeeDTO.class);
		
		//获取部门信息
		List<WeixinDepartmentDTO> depList = this. getWeixinDepList(request);
		Map<Integer,String>  departmentMap = new HashMap<Integer, String>();
		for(WeixinDepartmentDTO weixinDepartment :depList){
			if(!departmentMap.containsKey(weixinDepartment.getId())){//如果不包含部门id
				departmentMap.put(weixinDepartment.getId(), weixinDepartment.getName());
			}
		}
		//对人员部门进行封装
		for(WeixinEmployeeDTO weixinEmployeeDTO : employeeList){
			weixinEmployeeDTO.setDepName(departmentMap.get(weixinEmployeeDTO.getDepartment().get(0)));
		}
				
		return employeeList;
	}

	/**
	 *  获取微信通讯录部门信息集合
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception  抛出异常 
	 */
	@SuppressWarnings("static-access")
	@RequestMapping("/findAddressBookDepInfo.do")
   public   String   findAddressBookDepInfo(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
	  
	   //获取部门信息
	   List<WeixinDepartmentDTO>  departmentList = this.getWeixinDepList(request);
	   
	   JSONArray  jsonArray = new JSONArray().fromObject(departmentList);
	   model.addAttribute("departmentList", jsonArray);
	   
	   return "/weixin/findAddressBookDepInfo";
   }	
   
   
   /**
    *   获取微信通讯录部门信息集合
    * @param request
    * @return
    * @throws Exception
    */
   @SuppressWarnings({ "static-access", "unchecked" })
	public   List<WeixinDepartmentDTO>  getWeixinDepList(HttpServletRequest request) throws Exception{
		 //1.初始化appSetting.properties  配置文件
			AppSettingFactory  appSettingFactory = AppSettingFactory.getInstance();
			
			//2.获取所有部门链接
		   String department_infoUrl = appSettingFactory.getAppSetting("department_all_infoUrl")+WeiXinAccessToken.access_token;
	       String   department = 	WeiXinUtils.getWeiXinInfo(department_infoUrl, "department");
	       JSONArray  jsonDepArray = new JSONArray().fromObject(department);
	       List<WeixinDepartmentDTO>  departmentList =   (List<WeixinDepartmentDTO>) jsonDepArray.toCollection(jsonDepArray, WeixinDepartmentDTO.class);
	       departmentList= sortDepList(departmentList,request);
	       return departmentList;
	  }
	
}
