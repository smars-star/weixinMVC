<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
  <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
  <title>微信通讯录部门列表</title>
  
  <link rel="stylesheet" href="../../media/bootstrap/css/bootstrap.css">
  <link rel="stylesheet" href="../../media/bootstrap/css/bootstrap-theme.css">
  
   <link rel="stylesheet" href="../../media/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
  
  <%@include file="/common/taglib.jsp"%>
  
  <style type="text/css">
    
  </style>
  
</head>
<body>
<form action="findAddressBookEmpInfo.do" method="post" onsubmit="return false;">
 
 <input type="hidden"  name="department" id="departmentID"  value="${department }">

 
 <div style="width:260; vertical-align:top; BORDER-RIGHT: #999999 1px dashed">
	 <ul id="tree" class="ztree" style="width:260px; overflow:auto;"></ul>
 </div>
 
 
 <div style="position: fixed; left:260px;  top:0px;  height:850px; overflow: auto">
 	 <table >
	<tr>
		<td style="vertical-align:top">
		     <div style="float: right;margin-right: 5px;margin-top: 5px;">
			      <button class="btn btn-primary btn-lg" type="submit" onclick="addInitEmp();">新增人员</button>
			 </div>
		
			     <table class="table table-condensed showDepEmploy">
			         <thead>
			                <tr>
						                  <td style="width:5%">操作</td>
						                  <td style="width:2%">员工ID</td>
						                  <td style="width:8%">员工名称</td>
						                  <td style="width:10%">部门名称</td>
						                  <td style="width:12%">职位</td>
						                  <td style="width:6%">电话</td>
						                  <td style="width:4%">性别</td>
						                  <td style="width:8%">email</td>
						                  <td style="width:3%">微信号</td>
						                  <td style="width:4%">头像</td>
						                  <td style="width:4%">是否关注</td>
						                  <td style="width:4%">扩展信息</td>
					        </tr>
			         </thead>
			         <tbody>
			        <c:choose>
			              <c:when test="${empty employeeList}">
			                    <tr>
			                             <td colspan="11">对不起，暂无人员信息！</td>
			                    </tr>
			              </c:when>
			              <c:otherwise>
			                    <c:forEach items="${employeeList }"  var="employeeDTO">
			                          <tr>
							                  <td>
							                    <a href="#" onclick="deleteEmp('${employeeDTO.userid}')"><img alt="删除" src="../../media/images/btn_delete.png"></a>
							                  </td>
							                  <td>${employeeDTO.userid}</td>
							                  <td><a href="#" onclick="modifyInitEmp('${employeeDTO.userid}');">${employeeDTO.name}</a></td>
							                  <td>${employeeDTO.depName}</td>
							                  <td>${employeeDTO.position}</td>
							                  <td>${employeeDTO.mobile}</td>
							                  <td>
							                         <c:choose>
							                              <c:when test="${employeeDTO.gender eq 1}">男</c:when>
							                              <c:otherwise>女</c:otherwise>
							                         </c:choose>
							                  </td>
							                  <td>${employeeDTO.email}</td>
							                  <td>${employeeDTO.weixinid}</td>
							                  <td>
							                  
							                        <c:choose>
							                              <c:when test="${empty employeeDTO.avatar}"><img alt="${employeeDTO.name}" src="../../media/images/userFace.jpg" style="width: 20px;width: 20px;position: static;"></c:when>
							                              <c:otherwise><img alt="${employeeDTO.name}" src="${employeeDTO.avatar}" style="width: 20px;width: 20px;position: static;"></c:otherwise>
							                         </c:choose>
							                        
							                 </td>
							                  <td>
							                  
							                  <c:choose>
							                        <c:when test="${employeeDTO.status eq 1}">
							                           是
							                        </c:when>
							                        <c:when test="${employeeDTO.status eq 2}">
							                          已冻结
							                        </c:when>
							                        <c:otherwise>否</c:otherwise>
							                  </c:choose>
						
							                  </td>
							                  <td><c:if test="${not empty employeeDTO.extattr.attrs}">${employeeDTO.extattr.attrs}</c:if></td>
					                  </tr>
			                    </c:forEach>
			                       
			              </c:otherwise>
			        </c:choose>
			         </tbody>
				</table>
		 </td>
	</tr>
</table>
 </div>

 
</form>	
 
  <!-- <script type="text/javascript" src="../../media/js/jquery/jquery.js"></script> -->
  <script type="text/javascript" src="../../media/zTree/js/jquery-1.4.4.min.js"></script>
  <script type="text/javascript" src="../../media/zTree/js/jquery.ztree.core.js"></script>
  
  
  <script type="text/javascript">
  
    var zTree;
	var demoIframe;

	var setting = {
		view: {
			dblClickExpand: false,
			showLine: true,
			selectedMulti: false
		},
		data: {
			simpleData: {
				enable:true,
				idKey: "id",
				pIdKey: "parentid",
				rootPId: "1"
			}
		},
		callback: {
			beforeClick: function(treeId, treeNode) {
				var zTree = $.fn.zTree.getZTreeObj("tree");
				showDepEmployee(treeNode.id);
				/* if (treeNode.isParent) {
					zTree.expandNode(treeNode);
					return false;
				} else {
					showDepEmployee(treeNode.id);
					return true;
				} */
			}
		}
	};

	//部门信息
	var zNodes =${departmentList};

	$(document).ready(function(){
		var t = $("#tree");
		t = $.fn.zTree.init(t, setting, zNodes);
		demoIframe = $(".showDepEmploy");
		demoIframe.bind("load", loadReady);
		var zTree = $.fn.zTree.getZTreeObj("tree");
		
		//展开所有节点
		zTree.expandAll(true); 
		
		//默认选择那个节点
		//zTree.selectNode(zTree.getNodeByParam("id", 101));

	});

	function loadReady() {
		var bodyH = demoIframe.contents().find("body").get(0).scrollHeight,
		htmlH = demoIframe.contents().find("html").get(0).scrollHeight,
		maxH = Math.max(bodyH, htmlH), minH = Math.min(bodyH, htmlH),
		h = demoIframe.height() >= maxH ? minH:maxH ;
		if (h < 530) h = 530;
		demoIframe.height(h);
	}
  
	//根据部门ID
   function  showDepEmployee(departmentID){
	   
	   $.ajax({
		   type: "POST",
		   url: "findDepEmployee.do",
		   data: "departmentID="+departmentID,
		   success: function(depEmpList){
			   
			  
			   var  department = 0;
		        var  showEmpListStr = "";
		        var depEmpList = eval(depEmpList);
		        
		        if(depEmpList.length >0){
		        
					        //展现该部门的人员信息
						    for(var i = 0; i < depEmpList.length;i++){
						    	//部门
						    	department = depEmpList[i].department;
						    	
						    	//性别
						    	var  genderStr = depEmpList[i].gender;
						    	if(genderStr == 1){
						    		genderStr = '男';
						    	}else{
						    		genderStr = '女';
						    	}
						    	//是否关注
						    	var  statusStr  = depEmpList[i].status;
						    	if(statusStr == 1){
						    		statusStr = "是";
						    	}else if(statusStr == 2){
						    		statusStr = '已冻结';
						    	}else{
						    		statusStr = '否';
						    	}
						    	
						    	//微信号ID
						    	var  wixinid  = depEmpList[i].weixinid ;
						    	if(wixinid == null || wixinid == 'undefined' || wixinid == ''){
						    		wixinid = '';
						    	}
						    	
						    	//判断是否有微信头像
						    	var  avatarStr = depEmpList[i].avatar;
						    	if(avatarStr == null || avatarStr == 'undefined' || avatarStr == '' ){
						    		avatarStr =  '<img src="../../media/images/userFace.jpg" style="width: 20px;width: 20px;position: static;">';
						    	}else{
						    		avatarStr =  '<img src="'+ avatarStr +'" style="width: 20px;width: 20px;position: static;">';
						    	}
						    	  
						        showEmpListStr  += '<tr><td><a href="#" onclick="deleteEmp('+"'"+ depEmpList[i].userid+"'"+')"><img alt="删除" src="../../media/images/btn_delete.png"></a></td><td>'
						    	                                     +depEmpList[i].userid+'</td><td><a href="#"   onclick="modifyInitEmp('+"'"+depEmpList[i].userid+"'"+');">'+depEmpList[i].name+'</a></td><td>'
						    	                                     +depEmpList[i].depName+"</td><td>"+depEmpList[i].position+"</td><td>"
						    	                                     +depEmpList[i].mobile+'</td><td>'+genderStr+'</td><td>'
						    	                                     +depEmpList[i].email+'</td><td>'+wixinid+'</td><td>'
						    	                                     +avatarStr+'</td><td>'+statusStr+'</td><td>'
						    	                                     +depEmpList[i].extattr.attrs+'</td></tr>';
						    	  
						    }
		        
		        }else{
		        	department = departmentID;
		        	showEmpListStr = '<tr><td colspan="11">对不起，暂无人员信息！</td></tr>';
		        	
		        }
		        
		        $("#departmentID").val(department);
			    $(".showDepEmploy tbody").html(showEmpListStr); 
		   },error: function(e) {
	            alert("对不起，由于网络原因系统没有反映！<br/>请重新进入应用！");
	      }
		});
	   
   }	
	
	//打开微信人员信息
	function  modifyInitEmp(userid){
		  window.open("/weixin/modifyInitEmp.do?userid="+userid);
	}
	
	//删除人员信息
	function  deleteEmp(userid){
		
		var a=confirm("您确定要删除吗？");
		if(a == true){
			window.open('/weixin/deleteEmp.do?userid=' + userid);
		}
	}
	
	
	  
	  //新增人员信息
	  function  addInitEmp(){
		   window.open("/weixin/addInitEmp.do");
	  }
	
  </script>
  
</body>
</html>