<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
  <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
  <title>微信 应用常用应用二维码</title>
  
  <link rel="stylesheet" href="../../media/bootstrap/css/bootstrap.css">
  <link rel="stylesheet" href="../../media/bootstrap/css/bootstrap-theme.css">
  
  
  <%@include file="/common/taglib.jsp"%>
  
  <style type="text/css">
    
  </style>
  
</head>
<body>
<form action="findAllQRCode.do" method="post" onsubmit="return false;">
 
<h1 class="text-center">微信通讯录部门信息</h1>	<br /> 
	


	
<div style="width:100%;vertical-align: top;margin-top: 25px;line-height: 40px;text-align: center;padding: 10px;">
		    <div class="row">
            <c:forEach  items="${qrCodeImageStrMap }" var="qrCodeImageStr">
							  
							  <div class="col-sm-6 col-md-4">
							    <div class="thumbnail">
							           <a href="#" > <img alt=" ${qrCodeImageStr.key}" src="${qrCodeImageStr.value}"></a>
							           <div class="caption">
							               <h3> ${qrCodeImageStr.key}</h3>
							  	       </div>
							    </div>
							  </div>
							  
							  
            </c:forEach>
			</div>
</div>	

 
</form>	
 
  <!-- <script type="text/javascript" src="../../media/js/jquery/jquery.js"></script> -->
  <script type="text/javascript" src="../../media/zTree/js/jquery-1.4.4.min.js"></script>
  
</body>
</html>