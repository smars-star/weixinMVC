package gds.com.weixin.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * <p> Title:微信公共类</p>
 * <p> Description: 微信部门员工DTO </p>
 * <p>  Copyright: Copyright (c) 2017>  </p>
 * <p> Company: 刘云鹏</p>
 * @author liuyunpeng
 * @version $Revision: 1.0 $
 */
@XmlRootElement
public class WeixinEmployeeDTO implements Serializable {

	/**
	 *  ����Ψһ���к�
	 */
	private static final long serialVersionUID = 985782072975019760L;

	/** ��ԱUserID����Ӧ����˵��ʺ�  */
	private  String  userid ="";
	/** ��Ա���� */
	private  String  name = "";
	/** 	��Ա��������id�б� */
	private  List<Integer>  department = null;
	/** �������� */
	private  String   depName = "";
	/** 	ְλ��Ϣ */
	private  String  position = "";
	/** 	�ֻ����롣��������ͨѶ¼�׼��ɻ�ȡ */
	private  String  mobile = "";
	/** �Ա�0��ʾδ���壬1��ʾ���ԣ�2��ʾŮ�� */
	private  String  gender = "";
	/** 	���䡣��������ͨѶ¼�׼��ɻ�ȡ */
	private  String  email = "";
	/** 	΢�ź� */
	private  String  weixinid = "";
	/** ͷ��url��ע�����Ҫ��ȡСͼ��url����"/0"�ĳ�"/64"���� */
	private  String  avatar = "";
	/** ��ע״̬: 1=�ѹ�ע��2=�Ѷ��ᣬ4=δ��ע */
	private  int  status = 1;
	/** �����ڵ����� */
	private  List<Integer> order= null;
	/** ��չ���ԡ���������ͨѶ¼�׼��ɻ�ȡ */
	private  Map<String,Map<String,?>>  extattr = null;
	
	/** �Ƿ�ɹ� */
    private  String  errmsg = "";
    /** ����ԭ����� */
	private  String  errcode = "";
	/** �Ƿ��쵼 */
	private  int  isleader = 0;
	 /** Ӣ������ */
	private  String english_name = "";
	/** ���� */
	private  String  telephone = "";
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWeixinid() {
		return weixinid;
	}
	public void setWeixinid(String weixinid) {
		this.weixinid = weixinid;
	}

	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public List<Integer> getOrder() {
		return order;
	}
	public void setOrder(List<Integer> order) {
		this.order = order;
	}
	public List<Integer> getDepartment() {
		return department;
	}
	public void setDepartment(List<Integer> department) {
		this.department = department;
	}
	public Map<String,Map<String,?>> getExtattr() {
		return extattr;
	}
	public void setExtattr(Map<String,Map<String,?>> extattr) {
		this.extattr = extattr;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public String getErrcode() {
		return errcode;
	}
	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getEnglish_name() {
		return english_name;
	}
	public void setEnglish_name(String english_name) {
		this.english_name = english_name;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public int getIsleader() {
		return isleader;
	}
	public void setIsleader(int isleader) {
		this.isleader = isleader;
	}

}
