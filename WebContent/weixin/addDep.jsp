<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
  <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
  <title>新增微信部门</title>
  
  <link rel="stylesheet" href="../../media/bootstrap/css/bootstrap.css">
  <link rel="stylesheet" href="../../media/bootstrap/css/bootstrap-theme.css">
  
  <%@include file="/common/taglib.jsp"%>
  
  <style type="text/css">
    
  </style>
  
</head>
<body>
<form action="addDep.do" method="post" onsubmit="return false;">
  
  <div style="max-width: 85%;vertical-align: top;margin-top: 25px;line-height: 40px;" align=center>
 <table  style="vertical-align:top;max-height: 850px;overflow: auto;">
	   <tr>
	           <td class="text-right">部门名称：</td>
	           <td>
	                  <input name="name" type="text" >
	           </td>
	   </tr>
	   <tr>
	           <td class="text-right">父部门：</td>
	           <td>
	                    <select name="parentid">
	                        <option value=""> -- 请选择 -- </option>
	                        <c:forEach items="${departmentList }" var="departmentDTO">
	                         <option value="${departmentDTO.id }">${departmentDTO.name }</option>
	                        </c:forEach>
	                    </select>
	           </td>
	   </tr>

</table>
 
  <div  style="margin-top: 10px;padding-left: 20px;">
     <button type="button" class="btn btn-primary" onclick="addSave();">保存</button>
      <button type="button" class="btn btn-default"  onclick="window.close()"> 取消</button>
  </div>
  
 </div>
</form>	
 
  <!-- <script type="text/javascript" src="../../media/js/jquery/jquery.js"></script> -->
  <script type="text/javascript" src="../../media/zTree/js/jquery-1.4.4.min.js"></script>
  
  
  <script type="text/javascript">
    
  //保存
   function addSave(){
	    document.forms[0].submit();
   }

  
  </script>
  
</body>
</html>