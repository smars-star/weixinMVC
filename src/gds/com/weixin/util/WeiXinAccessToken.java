package gds.com.weixin.util;



import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import gds.com.framework.util.AppSettingFactory;

/**
 * <p>Title: 微信获取访问微信企业号access_token类</p>
 * <p> Description: 每间隔两个小时重新获取一下和微信企业类之间的 access_token </p>
 * <p> Copyright: Copyright (c) 2017>
 * 版权：Copyright (c) 2017 刘云鹏<br>
 * @author liuyunpeng
 * @version $Revision: 1.0 $
 */
public class WeiXinAccessToken implements ServletContextListener  {
//public class WeiXinAccessToken extends HttpServlet  {
	
	//@Autowired
	//private  HttpServletRequest request;

	/**
	 * 
	 */
	private static final long serialVersionUID = -2350265212524988812L;

	/** AccessToken是企业号的全局唯一票据，调用接口时需携带AccessToken */
	public static String access_token = "";

	private Timer timer = null;
	
	public static Map<String,String> employeeMap = new HashMap<String,String>();

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		timer.cancel();
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		// 初始化AppSettingFactory
		AppSettingFactory appSettingFactory = AppSettingFactory.getInstance();
		String accessTokenTimeStr =   appSettingFactory.getAppSetting("accessTokenTime") ;
		int accessTokenTime = Integer.parseInt(accessTokenTimeStr);// access_token有效时间

		timer = new Timer(true);// 创建一个新计时器，可以指定其相关的线程作为守护程序运行。
		// 设置任务计划，启动和间隔时间
		timer.schedule(new contractTask(), 0, accessTokenTime);
		
		
		servletContextEvent.getServletContext();
		


		
	/*  try {
			//把accesstoken放到session中
			HttpServletRequest request =((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
			HttpSession session = request.getSession();
			String accessToken_session = session.getAttribute("accessToken_session").toString();
			if(StringUtils.isNotEmpty(accessToken_session)) {
				session.setAttribute("accessToken_session", accessToken_session);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
	}
	


	/**
	 * 
	* <p>Title: 用类每间隔2个小时来重新获取一下access_tokne的静态内部类</p>
	* <p>Description: 每间隔两个小时重新获取一下和微信企业类之间的 access_token</p>
	* <p>Copyright: Copyright (c) 2017</p>
	* <p>Company: 长城数字[www.e-u.cn]</p>
	* @author liuyunpeng
	 * @version $Revision: 1.11 $
	 */
	class contractTask extends TimerTask {


		
		public void run() {
			
			//1.获取accss_token 
			excute();

		}

		private void excute() {
			try {
                
				Logger log = Logger.getLogger("微信端获取Access_token企业号唯一票据！");
				// 初始化AppSettingFactory
				AppSettingFactory appSettingFactory = AppSettingFactory.getInstance();
				String sCorpID = appSettingFactory.getAppSetting("corpID"); // 企业CorpID
				String adminSecret = appSettingFactory.getAppSetting("adminSecret");// 超级管理组
				
				//String  adminSecret = appSettingFactory.getAppSetting("addressBookSecret");//通讯录access_token
				String accessTokenURL = appSettingFactory.getAppSetting("accessTokenURL");// 获取访问微信
				
				// 访问微信服务器,获取access_token的url链接
				String url = accessTokenURL + "corpid=" + sCorpID + "&corpsecret=" + adminSecret;
				String tempAccess_token = WeiXinUtils.getWeiXinInfo(url, "access_token");
				access_token = tempAccess_token;
				
				
				
				// 打印access_token日志
				log.info("获取Access_token:" + access_token);
				
			} catch (Exception e) {    
				e.printStackTrace();   
				try {
					Thread.sleep(60000);  //线程睡眠1分钟后，再次执行
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				excute();
			}
		}

	}



}
