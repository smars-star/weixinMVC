package gds.com.weixin.util;

import gds.com.weixin.dto.WeixinMessageDTO;

import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.lang3.StringUtils;

import net.sf.json.JSONObject;

/**
 * 文件名：微信WeixinUtil工具类<br>
 * 版权：Copyright (c) 2017 刘云鹏<br>
 * 描述：微信测系统，主要包括微信的access_token、管理通讯录、发送微信消息、微信JS-SDK等操作。<br>
 * 修改人：Author: liuyunpeng<br>
 * 版本：Revision: 1.0
 */
public class WeiXinUtils {
	 
	   /**
		 *   根据url链接和要获取的字段，查询要获取的字段的value值
		 * @param url   要访问的url链接
		 * @param messageName 要获取的字段信息
		 * @return    返回要获取的字段信息内容
		 * @throws AppException 抛出异常
		 * @author liuyunpeng
		 */
		public static String   getWeiXinInfo(String url,String messageName) throws Exception{
			
			// 链接
			URL getUrl = null;
				// 创建一个管理证书的任务管理器
				//TrustManager[] tm = { new gds.office.weixin.weixin.dto.MyX509TrustManager() };
				SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
				sslContext.init(null, null, new java.security.SecureRandom());
				// 从上述SSLContext对象中得到SSLSocketFactory对象
				SSLSocketFactory ssf = sslContext.getSocketFactory();

				getUrl = new URL(url);
				HttpsURLConnection http = (HttpsURLConnection) getUrl.openConnection();
				http.setSSLSocketFactory(ssf);
				http.setRequestMethod("GET");
				http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				http.setDoOutput(true);
				http.setDoInput(true);

				http.connect();
				InputStream is = http.getInputStream();
				
				
				StringBuilder sb = new StringBuilder();
				int i = 0; 
				byte[] b= new byte[1024];
				
			    while((i = is.read(b)) != -1){
			    	String read = new String(b, 0, i,"UTF-8");
			    	sb.append(read);
			    }
				is.close();
				
				JSONObject json = JSONObject.fromObject(sb.toString());
				if(messageName.isEmpty()){
					messageName =json.toString();
				}else{
					messageName =json.get(messageName).toString();
				}
				
			return messageName;
		}
		
		/**
		 *    发送企业微信消息
		 * @param accessToken  访问企业微信接口的证书
		 * @param url                     发送消息链接 
		 * @param toUser              消息接收人(人员NO)
		 * @param agentID            应用ID
		 * @param content            消息内容 content:文本消息、voice:语音消息、file：文件消息、textcard：卡片消息内容、news：图文消息内容
		 * @param msgtype          消息类型 
		 * @throws Exception      抛出异常
		 * @author liuyunpeng
		 */
		@SuppressWarnings("static-access")
		public static  void  sendWeixinMessageByPost(String url,String accessToken,int agentID,String toUser,String content,String msgtype) throws Exception{
			if(!url.isEmpty()){
			
					//1. 创建一个管理证书的任务管理器
					//TrustManager[] tm = { new gds.office.weixin.weixin.dto.MyX509TrustManager() };
					SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
					sslContext.init(null, null, new java.security.SecureRandom());
		
					//2.1 从上述SSLContext对象中得到SSLSocketFactory对象
					SSLSocketFactory ssf = sslContext.getSocketFactory();
					URL getUrl = null;
					
					//2.2对数据进行分装
					WeixinMessageDTO  userDTO = new WeixinMessageDTO();
					userDTO.setAgentid(agentID);
					userDTO.setMsgtype(msgtype);
					userDTO.setTouser(toUser);
					userDTO.setSafe(0);
					Map<String,String>  text	 =  new HashMap<String,String>();
					text.put("content", content);
					userDTO.setText(text);
					
					JSONObject jsonObject = new JSONObject().fromObject(userDTO);
					
					//3. 打开URL链接
					getUrl = new URL(url);
					HttpsURLConnection http = (HttpsURLConnection) getUrl.openConnection();
					http.setSSLSocketFactory(ssf);
		
					http.setRequestMethod("POST");
					http.setRequestProperty("Content-Type", "application/json;charset=utf-8"); 
					http.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
					http.setRequestProperty("Charset", "UTF-8");
					http.setDoOutput(true);
					http.setDoInput(true);
					http.connect();
					
					//4.1建立输入流，向指向的URL传入参数
					OutputStreamWriter   osw = new OutputStreamWriter(http.getOutputStream(), "UTF-8");
					osw.write(jsonObject.toString());
					osw.flush();
					osw.close();
				    
				   // 4.2这句才是真正发送请求  
				   http.getInputStream();  
		   
			}
		}
		
		
		/**
		 * 
		 *  使用http,对微信发送对象信息
		 * @throws Exception   抛出异常
		 * @author liuyunpeng
		 */
		@SuppressWarnings("static-access")
		public static  String   sendHttpWeixinBodyByPost(String url ,Object object,String  errmsg) throws Exception{
			
			//1. 创建一个管理证书的任务管理器
			//TrustManager[] tm = { new gds.office.weixin.weixin.dto.MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, null, new java.security.SecureRandom());

			//2.1 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL getUrl = null;
			
			JSONObject jsonObject = new JSONObject().fromObject(object);
			
			//3. 打开URL链接
			getUrl = new URL(url);
			HttpsURLConnection http = (HttpsURLConnection) getUrl.openConnection();
			http.setSSLSocketFactory(ssf);

			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type", "application/json;charset=utf-8"); 
			http.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
			http.setRequestProperty("Charset", "UTF-8");
			http.setDoOutput(true);
			http.setDoInput(true);
			http.connect();
			
			//4.1建立输入流，向指向的URL传入参数
			OutputStreamWriter   osw = new OutputStreamWriter(http.getOutputStream(), "UTF-8");
			osw.write(jsonObject.toString());
			osw.flush();
			osw.close();
		    
		   // 4.2这句才是真正发送请求  
		   http.getInputStream();  	
		   
		   //5.返回信息
		   InputStream  is = http.getInputStream();
           int size  = is.available();
	       byte[] b = new byte[size];
		   is.read(b);
		   is.close();
		   String message = new String(b, "UTF-8");
		 
          JSONObject json = JSONObject.fromObject(message);
       
           return json.get(errmsg).toString();
		}
		
		
		
		/**
		 * 
		 *  使用http,对微信发送对象信息
		 * @throws Exception   抛出异常
		 * @author liuyunpeng
		 */
		@SuppressWarnings("static-access")
		public static  String   sendHttpWeixinBodyByGet(String url ,String str,String  errmsg) throws Exception{
			
			//1. 创建一个管理证书的任务管理器
			//TrustManager[] tm = { new gds.office.weixin.weixin.dto.MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, null, new java.security.SecureRandom());

			//2.1 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL getUrl = null;
			
		
			
			//3. 打开URL链接
			getUrl = new URL(url);
			HttpsURLConnection http = (HttpsURLConnection) getUrl.openConnection();
			http.setSSLSocketFactory(ssf);

			http.setRequestMethod("GET");
			http.setRequestProperty("Content-Type", "application/json;charset=utf-8"); 
			http.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
			http.setRequestProperty("Charset", "UTF-8");
			http.setDoOutput(true);
			http.setDoInput(true);
			http.connect();
			
			//4.1建立输入流，向指向的URL传入参数
			if(!StringUtils.isEmpty(str)){
				JSONObject jsonObject = new JSONObject().fromObject(str);
				OutputStreamWriter   osw = new OutputStreamWriter(http.getOutputStream(), "UTF-8");
				osw.write(jsonObject.toString());
				osw.flush();
				osw.close();
			}
		   
			 // 4.2这句才是真正发送请求  
	        http.getInputStream();  	
			
		   //5.返回信息
		   InputStream  is = http.getInputStream();
           int size  = is.available();
	       byte[] b = new byte[size];
		   is.read(b);
		   is.close();
		   String message = new String(b, "UTF-8");
		 
          JSONObject json = JSONObject.fromObject(message);
       
           return json.get(errmsg).toString();
		}
		
	
		
	/**
	 *   获取人员微信头像 
	 * @param employeeNo  人员编号
	 * @param user_userid_infoUrl  获取微信人员头像url
	 * @return   photoUrl 返回人员微信头像地址
	 * @throws Exception  抛出异常
	 * @author liuyunpeng
	 * @throws AppException 
	 */
	public static   String  getWeixinPhotoUrl(String user_userid_infoUrl,String employeeNo) {
	        String  photoUrl  = "";//微信头像httpUrl链接
			try {
				 //从微信里面获取当前登录人微信头像
				  photoUrl = WeiXinUtils.getWeiXinInfo(user_userid_infoUrl+"access_token="+ WeiXinAccessToken.access_token+"&userid="+employeeNo, "avatar");//获取当前登录人微信头像
			} catch (Exception e) {
				e.printStackTrace();
			}
			return photoUrl;
	}
	
	
	/** 
     * 将对象直接转换成String类型的 XML输出 
     *  
     * @param obj 
     * @return 
     */  
    public static String convertToXml(Object obj) {  
        // 创建输出流  
        StringWriter sw = new StringWriter();  
        try {  
            // 利用jdk中自带的转换类实现  
            JAXBContext context = JAXBContext.newInstance(obj.getClass());  
            Marshaller marshaller = context.createMarshaller();  
            // 格式化xml输出的格式  
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,   Boolean.TRUE);  
            // 将对象转换成输出流形式的xml  
            marshaller.marshal(obj, sw);  
        } catch (JAXBException e) {  
            e.printStackTrace();  
        }  
        return sw.toString();  
    }  
	

	/**
	 * 解决跨域JSON数据乱码问题
	 * 
	 * @param response
	 * @return
	 * @author liuyunpeng
	 */
	public static HttpServletResponse setResponseContent(HttpServletResponse response) {
		// 解决乱码问题
		response.setContentType("text/html; charset=utf-8");
		// 解决ajax跨域问题
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		return response;
	}

}
