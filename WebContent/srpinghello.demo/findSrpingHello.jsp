<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

  <title>部门人员查询</title>
  
  	<%
	request.setAttribute("sys_titleInfo", "部门人员查询");
    %>
  <%@include file="/common/taglib.jsp"%>
  <%@include file="/common/js_css.jsp"%>
  <%--@include file="/common/JQuery.jsp"--%>
  
</head>
<body>
  <%@include file="/common/head.jsp"%>
  
<form action="findSrpingHello.do" method="post" onsubmit="return false;">


<div class="row" >
	<div class="col-lg-6" style="width:100%;">
	   <div class="input-group">
		      <input type="text" class="form-control"  name="employeeName"  value="${map.employeeName }" placeholder="请输入名字或部门">
		      <span class="input-group-btn">
		        <button class="btn btn-default " type="button"  id="queryEmployeeName">查询</button>
		      </span>
	   </div>
	</div>
</div>

<div class="panel panel-default">
  <div class="panel-heading text-center">公司人员信息查询</div>
    
    <display:table name="employeeList" id="row" pagesize="15" export="true" class="table text-left"  requestURI="findSrpingHello.do" >
	   <display:column property="employeeName" title="名字" sortable="true" headerClass="sortable"  value="${row.employeeName }"/>
	   <display:column property="genderCodeValue" title="性别" sortable="true" headerClass="sortable"  value="${row.genderCodeValue }"/>
	   <display:column property="workDepName" title="部门名称" sortable="true" headerClass="sortable"  value="${row.workDepName }"/>
	   <display:setProperty name="export.csv.filename" value="StaffInfo.csv"/>
	   <display:setProperty name="export.excel.filename" value="StaffInfo.xls"/>
   </display:table>
   
</div>
	
</form>	

  <script type="text/javascript">
       
       //查询人员信息
       $("#queryEmployeeName").click(function(){
    	     document.forms[0].submit();
       });  
  </script>
  
</body>
</html>