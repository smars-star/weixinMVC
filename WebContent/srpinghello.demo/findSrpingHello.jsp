<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
  <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
  <title>spring Hello Title</title>
  
  
  <link rel="stylesheet" href="../../media/bootstrap/css/bootstrap.css">
  <link rel="stylesheet" href="../../media/bootstrap/css/bootstrap-theme.css">
  <%@include file="/common/taglib.jsp"%>
  
</head>
<body>
<form action="findSrpingHello.do" method="post" onsubmit="return false;">

<h1 class="text-center">部门人员查询</h1>	<br /> 

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

	  <table class="table text-left">
	         <thead>
				<tr>
					<th class="text-center">名字</th>
					<th class="text-center">性别</th>
					<th class="text-center">部门名称</th>
				</tr>
			</thead>
			<tbody>
	             <c:choose>
	                    <c:when test="${empty employeeList }">
						                 <tr>
						                     <td colspan="3">对不起，暂无信息显示!</td>
						                 </tr>
	                    </c:when>
	                    <c:otherwise>
	                                 <c:forEach items="${employeeList }" var="employeeDTO">
	                                      <tr>
						                     <td>${employeeDTO.employeeName }</td>
						                     <td>${employeeDTO.genderCodeValue }</td>
						                     <td>${employeeDTO.workDepName }</td>
						                 </tr>
	                                 </c:forEach>
	                    </c:otherwise>
	             </c:choose>
			</tbody>
	  </table>
</div>
	
</form>	
 
  <script src="../../media/js/jquery/jquery.js"></script>
  
  <script type="text/javascript">
       
       //查询人员信息
       $("#queryEmployeeName").click(function(){
    	     document.forms[0].submit();
       });  
  </script>
  
</body>
</html>