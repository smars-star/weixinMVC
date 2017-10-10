package gds.com.common.base;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * 
 * <p>Description: sqlTemplate </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company:   刘云鹏<br></p>
 * @date 创建时间：4 Sep 2017 11:52:36
 * @author liuyunpeng
 * @version  1.0
 */
public class BaseDao extends SqlSessionDaoSupport {
	
	/** Mybatis 查询模版 */
	@Resource(name="sqlSessionTemplate")
	public  SqlSessionTemplate  sqlSessionTemplate;
	
   
	/**
	 *  SqlSession工厂，实现SqlSessionDaoSupport的setSqlSesssionFactory 方法，
	 *  用来将spring数据源成功注入
	 */
	@Resource
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory ){
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
}
