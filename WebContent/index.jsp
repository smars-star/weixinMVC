<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
  <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
  <title>spring Hello Title</title>
  

  <link rel="stylesheet" href="/media/bootstrap/css/bootstrap.css">
  <link rel="stylesheet" href="/media/bootstrap/css/bootstrap-theme.css">
  <%@include file="/common/taglib.jsp"%>
  <%@include file="/common/js_css.jsp"%>
  
</head>
<body>
        
       <div class="list-group">
		   <a href="./test/findSrpingHello.do" class="list-group-item list-group-item-success" >查询人员(部门)信息</a>
		   <a href="./weixin/findAddressBookEmpInfo.do" class="list-group-item list-group-item-info">微信通讯录人员维护</a>
		   <a href="./weixin/findAddressBookDepInfo.do" class="list-group-item list-group-item-warning">微信通讯录部门维护</a>
		   <a href="./qrcode/findAllQRCode.do" class="list-group-item list-group-item-danger">常用链接二维码入口(网页授权) </a>
		   <a href="./pageAuthorization/findPageAuthorization.do" class="list-group-item list-group-item-success" >网页应用(JS-SDK)</a>
		   <a href="javascript:alert('暂未开通！');" class="list-group-item list-group-item-success" >企业微信支付</a>
		   <a href="./qrcode/findBase64QRCode.jsp" class="list-group-item list-group-item-info">二维码生成工具</a>
		   <!--    <a href="./weixin/findAddressBookDepInfo.do" class="list-group-item list-group-item-warning">微信通讯录部门维护</a>
		   <a href="./qrcode/findAllQRCode.do" class="list-group-item list-group-item-danger">常用链接二维码入口(网页授权) </a>
		   <a href="./pageauthoriza/findPageAuthorization.jsp" class="list-group-item list-group-item-success" >网页应用</a><br>
		     -->
		   
		   <div style="position: relative;margin-top: 200px;" align="center">
		      <img alt="企业号二维码" src="../../media/images/qrcode_gds_small.png"   class="img-responsive">
		       <div style="margin-bottom: -30px;"><h4>长城数字企业号二维码</h4></div>
		   </div>
		   
		</div>
        
        

</body>
</html>