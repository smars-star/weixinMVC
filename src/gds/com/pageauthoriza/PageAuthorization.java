package gds.com.pageauthoriza;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import gds.com.weixin.util.AesException;
import gds.com.weixin.util.SHA1;
import gds.com.weixin.util.WeiXinAccessToken;

/**
 * 
 * <p>Description:网页应用授权、JS-SDK网页应用 </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company:   刘云鹏<br></p>
 * @date 创建时间：4 Sep 2017 11:52:36
 * @author liuyunpeng
 * @version  1.0
 */
@RequestMapping("/pageAuthorization")
@Controller
public class PageAuthorization {

	
	/**
	 *   网页应用首页
	 * @param request
	 * @param response
	 * @param model
	 * @return  跳转到网页应用首页
	 * @throws IOException   抛出异常
	 */
	@RequestMapping("/findPageAuthorization.do")
	public  String  findPageAuthorization(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException{
       // 获取session
	   HttpSession session = request.getSession();
	   //把access_token放到session中
	   session.setAttribute("accessToken_session", WeiXinAccessToken.access_token);
	   
	   //JS-SDK 签名标签,有效时间为7200s(2小时)
	   String  jsapi_ticket = null;
	   try {
		   String  url = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getRequestURI();  
		   jsapi_ticket = SHA1.getSHA1(WeiXinAccessToken.access_token,"1504489723","h2z7e42b5gqtzkt",url);
		   session.setAttribute("jsapi_ticket", jsapi_ticket);
       } catch (AesException e) {
		  e.printStackTrace();
	   }
	
	    return "/pageAuthorization/findPageAuthorization";
		
	}

}
