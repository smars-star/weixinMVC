package gds.com.weixin.dto;

import java.io.Serializable;

/**
 * 
 * <p> Title:微信公共类</p>
 * <p> Description: 微信部门DTO </p>
 * <p>  Copyright: Copyright (c) 2017>  </p>
 * <p> Company: 刘云鹏</p>
 * @author liuyunpeng
 * @version $Revision: 1.0 $
 */
public class WeixinDepartmentDTO implements Serializable {

	/**
	 *  生成唯一序列号
	 */
	private static final long serialVersionUID = 4745698008753552455L;
	
	/** 部门ID */
	private  int id = 0;
	/** 	部门名称  */
	private  String name = "";
	/** 	父亲部门id。根部门为1  */
	private  int parentid;
	/** 		在父部门中的次序值。order值小的排序靠前  */
	private  int order;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getParentid() {
		return parentid;
	}
	public void setParentid(int parentid) {
		this.parentid = parentid;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}

}
