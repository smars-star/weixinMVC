package gds.com.framework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


/**
 * 
 * <p>Description:字符集过滤器，默认走UTF-8格式</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company:   刘云鹏<br></p>
 * @date 创建时间：4 Sep 2017 11:52:36
 * @author liuyunpeng
 * @version  1.0
 */
public class SetCharacterEncodingFilter implements Filter {

	protected String encoding = null;
	protected FilterConfig filterConfig = null;

	@Override
	public void destroy() {
		this.encoding = null;
		this.filterConfig = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		String encoding = selectEncoding(request);
		if (encoding != null) {
			request.setCharacterEncoding(encoding);
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		encoding = filterConfig.getInitParameter("encoding");
	}
    
	/*
	 * 获取 encoding
	 */
	protected String selectEncoding(ServletRequest request) {
		return this.encoding;
	}

}
