package gds.com.weixin.dto;

import java.io.Serializable;

/**
 * 
 * <p> Title:΢�Ź�����</p>
 * <p> Description: ΢�Ų���DTO </p>
 * <p>  Copyright: Copyright (c) 2017>  </p>
 * <p> Company: ������</p>
 * @author liuyunpeng
 * @version $Revision: 1.0 $
 */
public class WeixinDepartmentDTO implements Serializable {

	/**
	 *  ����Ψһ���к�
	 */
	private static final long serialVersionUID = 4745698008753552455L;
	
	/** ����ID */
	private  int id = 0;
	/** 	��������  */
	private  String name = "";
	/** 	���ײ���id��������Ϊ1  */
	private  int parentid;
	/** 		�ڸ������еĴ���ֵ��orderֵС������ǰ  */
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
