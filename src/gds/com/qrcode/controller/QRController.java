package gds.com.qrcode.controller;

import gds.com.framework.util.AppSettingFactory;
import gds.com.weixin.util.QRCodeUtils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * <p>Description:二维码 </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company:   刘云鹏<br></p>
 * @date 创建时间：4 Sep 2017 11:52:36
 * @author liuyunpeng
 * @version  1.0
 */
@RequestMapping("/qrcode")
@Controller
public class QRController {

	/**
	 *   生成常用网页授权页面（二维码方式展示）
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findAllQRCode.do")
	public  String  findAllQRCode(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
		Map<String,String>  qrCodeImageStrMap  = new HashMap<String,String>();
		
		AppSettingFactory appSettingFactory = AppSettingFactory.getInstance();
		
		//现场更新应用链接
		String weixin_xcgc_app_url = appSettingFactory.getAppSetting("weixin_xcgc_app_url");
	    weixin_xcgc_app_url = QRCodeUtils.getBASE64AppQRCode(weixin_xcgc_app_url, 500, 500, "png");
	    qrCodeImageStrMap.put("现场更新", weixin_xcgc_app_url);
	    
		//日志管理应用链接
		String weixin_rzgl_app_url = appSettingFactory.getAppSetting("weixin_rzgl_app_url");
		weixin_rzgl_app_url = QRCodeUtils.getBASE64AppQRCode(weixin_rzgl_app_url, 500, 500, "png");
		 qrCodeImageStrMap.put("日志管理", weixin_rzgl_app_url);
		    
		//日志查询应用链接
		String weixin_rzch_app_url = appSettingFactory.getAppSetting("weixin_rzch_app_url");
		weixin_rzch_app_url = QRCodeUtils.getBASE64AppQRCode(weixin_rzch_app_url, 500, 500, "png");
		 qrCodeImageStrMap.put("日志查询", weixin_rzch_app_url);
		
		//部门通讯录应用链接
		String weixin_bmtxl_app_url = appSettingFactory.getAppSetting("weixin_bmtxl_app_url");
		weixin_bmtxl_app_url = QRCodeUtils.getBASE64AppQRCode(weixin_bmtxl_app_url, 500, 500, "png");
		 qrCodeImageStrMap.put("部门通讯录", weixin_bmtxl_app_url);
		
		model.addAttribute("qrCodeImageStrMap", qrCodeImageStrMap);
		
		return "/qrcode/findAllQRCode";
		
	}
	
	/**
	 *  生成二维码
	 * @param request
	 * @param response
	 * @param model
	 * @param map
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/createBase64QRCode.do")
	public  void    createBase64QRCode(HttpServletRequest request,HttpServletResponse response,Model model,@RequestParam Map<String,String> map) throws Exception{
		
		String content = map.get("content");//内容
		int  width= 500;
		if(map.containsKey("width")){
			width = Integer.parseInt(map.get("width"));
		}
		
		int height = 500;
		if(map.containsKey("height")){
			height = Integer.parseInt(map.get("height"));
		}
		
		String typeFormat = "png";
		if(map.containsKey("typeFormat")){
			typeFormat = map.get("typeFormat");
		}
		
		String qrBase64Image = QRCodeUtils.getBASE64AppQRCode(content, width, height, typeFormat);

		 response.getWriter().print(qrBase64Image);
		
	}
	
}
