<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

  <title>微信通讯录部门列表</title>
    
    <%
	request.setAttribute("sys_titleInfo", "微信通讯录部门列表");
    %>
    
  <%@include file="/common/taglib.jsp"%>
  <%@include file="/common/js_css.jsp"%>
  <%--@include file="/common/JQuery.jsp"--%>
  
</head>
<body>
  <%@include file="/common/head.jsp"%>
  
<form action="findAddressBookEmpInfo.do" method="post" onsubmit="return false;">
 
 <input type="hidden"  name="department" id="departmentID"  value="${department }">

 
 <div style="width:260; vertical-align:top; BORDER-RIGHT: #999999 1px dashed">
	 <ul id="tree" class="ztree" style="width:260px; overflow:auto;"></ul>
 </div>
 
      <div style="float: right;margin-right: 5px;margin-top: 5px;">
			      <button class="btn btn-primary btn-lg" type="submit" onclick="addInitEmp();">新增人员</button>
	 </div>
			 
     <div style="position: fixed; left:260px;  top:80px;  height:850px; overflow: auto">
		  
		   <display:table name="employeeList" id="row"  pagesize="15" export="true"  class="table table-condensed showDepEmploy"  requestURI="findAddressBookEmpInfo.do" >
			   <display:column title="操作" sortable="false" headerClass="sortable"  media="html">
			      <a href="#" onclick="deleteEmp('${row.userid}')"><img alt="删除" src="/media/images/btn_delete.png"></a>
			   </display:column>
			   <display:column title="员工ID" sortable="true" headerClass="sortable"  property="userid" />
			     
			   <display:column title="员工名称" sortable="true" headerClass="sortable" >
			      <a href="#" onclick="modifyInitEmp('${row.userid}');">${row.name}</a>
			   </display:column>
			   
			   <display:column property="depName" title="部门名称" sortable="true" headerClass="sortable" />
			   <display:column property="position" title="职位" sortable="true" headerClass="sortable"  />
			   <display:column property="mobile" title="电话" sortable="true" headerClass="sortable"  />
			   <display:column title="性别" sortable="true" headerClass="sortable" >
                              <c:choose>
                              <c:when test="${row.gender eq 1}">男</c:when>
                              <c:otherwise>女</c:otherwise>
                         </c:choose>
			   </display:column>
			   
			   <display:column property="email" title="email" sortable="true" headerClass="sortable"  autolink="true" />
			   <display:column property="weixinid" title="微信号" sortable="true" headerClass="sortable"  />
			     <display:column title="头像" sortable="true" headerClass="sortable" >
                             <c:choose>
                             <c:when test="${empty row.avatar}"><img alt="${row.name}" src="/media/images/userFace.jpg" style="width: 20px;width: 20px;position: static;"></c:when>
                             <c:otherwise><img alt="${row.name}" src="${row.avatar}" style="width: 20px;width: 20px;position: static;"></c:otherwise>
                          </c:choose>
			   </display:column>
			     <display:column title="是否关注" sortable="true" headerClass="sortable" >
                               <c:choose>
		                        <c:when test="${row.status eq 1}">是</c:when>
		                        <c:when test="${row.status eq 2}">已冻结</c:when>
		                        <c:otherwise>否</c:otherwise>
		                  </c:choose>
			   </display:column>
			   <display:column property="extattr.attrs" title="扩展信息" sortable="true" headerClass="sortable"  />
			  
			   <display:setProperty name="export.csv.filename" value="StaffInfo.csv"/>
			   <display:setProperty name="export.excel.filename" value="StaffInfo.xls"/>
		   </display:table>
				
  </div>

 
</form>	
 
  <script type="text/javascript" src="/media/zTree/js/jquery-1.4.4.min.js"></script>
  <script type="text/javascript" src="/media/zTree/js/jquery.ztree.core.js"></script>
  
  
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
						    		avatarStr =  '<img src="/media/images/userFace.jpg" style="width: 20px;width: 20px;position: static;">';
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