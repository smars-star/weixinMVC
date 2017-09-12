package gds.com.springhello.demo.dto;

import java.io.Serializable;
import java.util.Date;

public class EmployeeDTO implements Serializable {

	/**
	 *  生成唯一序列号
	 */
	private static final long serialVersionUID = -8218440900404463861L;

	private  String employeeID = "";
	private  String employeeName = "";
	private  String workdepID = "";
	private  String workDepName = "";
	private  int       genderCode = 1;
	private  String genderCodeValue = "";
	private  String employeeNo= "";
	private  Date  brithday ;
	
	
	public String getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getWorkdepID() {
		return workdepID;
	}
	public void setWorkdepID(String workdepID) {
		this.workdepID = workdepID;
	}
	public String getWorkDepName() {
		return workDepName;
	}
	public void setWorkDepName(String workDepName) {
		this.workDepName = workDepName;
	}
	public int getGenderCode() {
		return genderCode;
	}
	public void setGenderCode(int genderCode) {
		this.genderCode = genderCode;
	}
	public String getGenderCodeValue() {
		return genderCodeValue;
	}
	public void setGenderCodeValue(String genderCodeValue) {
		this.genderCodeValue = genderCodeValue;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public Date getBrithday() {
		return brithday;
	}
	public void setBrithday(Date brithday) {
		this.brithday = brithday;
	}
	
	
}
