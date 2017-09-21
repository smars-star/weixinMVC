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

	<!--[if lt IE 9]>
		<script src="/media/waterfall/js/css3-mediaqueries.js"></script>
	<![endif]-->
	<script type="text/javascript" src="/media/waterfall/js/jquery-1.8.3.min.js"></script>
	<!-- <script type="text/javascript" src="/media/waterfall/js/jQueryColor.js"></script> -->  
   	<!--这个插件是瀑布流主插件函数必须-->
	<script type="text/javascript" src="/media/waterfall/js/jquery.masonry.min.js"></script>
	<!--这个插件只是为了扩展jquery的animate函数动态效果可有可无-->
	<script type="text/javascript" src="/media/waterfall/js/jQeasing.js"></script>
	
  <script type="text/javascript">
  $(function(){

     /*  $("#showConent").scroll(function () {
          var viewH = $(this).height();//可见高度
          var contentH = $(this).get(0).scrollHeight;//内容高度
          var scrollTop = $(this).scrollTop();//滚动高度
          var count = $(".showDepEmploy").length;
          
          if (contentH - viewH - scrollTop<=100) {
              
              var  showDataLineCount  =  $("#showDataLineCountID").val();
               $.ajax({
                  url: "/test/findAjaxSpringHello.do?showDataLineCount=" +showDataLineCount ,
                  type: "POST",
                  success: function (data) {
                	  var  employeeList = eval(data);
                	  var  srollTableDate = "";
                	  for(var i = 0; i < employeeList.length; i++){
                		  srollTableDate += '<tr>'
	                                                          +'<td>'+employeeList[i].employeeName+'</td>'
	                                                          +'<td>'+employeeList[i].genderCodeValue+'</td>'
	                                                          +'<td>'+employeeList[i].workDepName+'</td>'
	                                                          +'</tr>';
                	  }
                	
                      $(".showDepEmploy tbody").append(srollTableDate);
                      console.log(showDataLineCount+":"+employeeList.length);
                      $("#showDataLineCountID").val(Number(showDataLineCount)+Number(employeeList.length));
                  }
              }); 
          }
      }); */
      
      
      
	  /*瀑布流开始*/
		var container = $('.showDepEmploy tbody');
		var loading=$('#imloading');
		// 初始化loading状态
		loading.data("on",true);
		/*判断瀑布流最大布局宽度，最大为1280*/
		function tores(){
			var tmpWid=$(window).width();
			if(tmpWid>1280){
				tmpWid=1280;
			}else{
				var column=Math.floor(tmpWid/320);
				tmpWid=column*320;
			}
			$('.showDepEmploy').width(tmpWid);
		}
		tores();
		$(window).resize(function(){
			tores();
		});
		container.imagesLoaded(function(){
		  container.masonry({
		  	columnWidth: 320,
		    itemSelector : '.item',
		    isFitWidth: true,//是否根据浏览器窗口大小自动适应默认false
		    isAnimated: true,//是否采用jquery动画进行重拍版
		    isRTL:false,//设置布局的排列方式，即：定位砖块时，是从左向右排列还是从右向左排列。默认值为false，即从左向右
		    isResizable: true,//是否自动布局默认true
		    animationOptions: {
				duration: 800,
				easing: 'easeInOutBack',//如果你引用了jQeasing这里就可以添加对应的动态动画效果，如果没引用删除这行，默认是匀速变化
				queue: false//是否队列，从一点填充瀑布流
			}
		  });
		});
		/*模拟从后台获取到的数据*/
		var sqlJson=[{'title':'瀑布流其实就是几个函数的事！','intro':'爆料，苏亚雷斯又咬人啦，C罗哪有内马尔帅，梅西今年要不夺冠，你就去泰国吧，老子买了阿根廷赢得彩票，输了就去不成了。','src':'images/one.jpeg','writer':'站长素材','date':'2小时前','looked':321},{'title':'瀑布流其实就是几个函数的事！','intro':'爆料了，苏亚雷斯又咬人啦，C罗哪有内马尔帅，梅西今年要不夺冠，你就去泰国吧，老子买了阿根廷赢得彩票，输了就去不成了。','src':'images/demo2.jpg','writer':'站长素材','date':'2小时前','looked':321},{'title':'瀑布流其实就是几个函数的事！','intro':'爆料了，苏亚雷斯又咬人啦，C罗哪有内马尔帅，梅西今年要不夺冠，你就去泰国吧，老子买了阿根廷赢得彩票，输了就去不成了。','src':'images/p1.jpg','writer':'站长素材','date':'2小时前','looked':321},{'title':'瀑布流其实就是几个函数的事！','intro':'爆料了，苏亚雷斯又咬人啦，C罗哪有内马尔帅，梅西今年要不夺冠，你就去泰国吧，老子买了阿根廷赢得彩票，输了就去不成了。','src':'images/p1.jpg','writer':'站长素材','date':'2小时前','looked':321}];
		/*本应该通过ajax从后台请求过来类似sqljson的数据然后，便利，进行填充，这里我们用sqlJson来模拟一下数据*/
		$(window).scroll(function(){
			if(!loading.data("on")) return;
			// 计算所有瀑布流块中距离顶部最大，进而在滚动条滚动时，来进行ajax请求，方法很多这里只列举最简单一种，最易理解一种
			var itemNum=$('#showDepEmploy').find('.item').length;
			
		/* 	 var viewH = $(this).height();//可见高度
	          var contentH = $(this).get(0).scrollHeight;//内容高度
	          var scrollTop = $(this).scrollTop();//滚动高度
	          var count = $(".showDepEmploy").length; */
	          
	       
		 	var itemArr=[];
			itemArr[0]=$('#showDepEmploy').find('.item').eq(itemNum).offset().top+$('#showDepEmploy').find('.item').eq(itemNum)[0].offsetHeight;
			/*	itemArr[1]=$('#showDepEmploy').find('.item').eq(itemNum-2).offset().top+$('#showDepEmploy').find('.item').eq(itemNum-1)[0].offsetHeight;
			itemArr[2]=$('#showDepEmploy').find('.item').eq(itemNum-3).offset().top+$('#showDepEmploy').find('.item').eq(itemNum-1)[0].offsetHeight;
			var maxTop=Math.max.apply(null,itemArr); */
			
			if(maxTop<$(window).height()+$(document).scrollTop()){
				console.log($(window).height() +":"+$(document).scrollTop() +":"+$(document).get(0).scrollHeight);
				//console.log(viewH +":"+contentH +":"+scrollTop);


        	   
          
				//加载更多数据
				loading.data("on",false).fadeIn(800);
       	
				(function(sqlJson){
					/*这里会根据后台返回的数据来判断是否你进行分页或者数据加载完毕这里假设大于30就不在加载数据*/
					if(itemNum>30){
						loading.text('就有这么多了！');
					}else{
						var html="";
						for(var i in sqlJson){
							
							html += '<tr>'
                                +'<td>'+sqlJson[i].title+'<img src="/media/images/btn_configuration.png"></td>'
                                +'<td>'+sqlJson[i].title+'</td>'
                                +'<td>'+sqlJson[i].title+'</td>'
                                +'</tr>';
						}
						
						/*模拟ajax请求数据时延时800毫秒*/
						var time=setTimeout(function(){
							$(html).find('img').each(function(index){
								loadImage($(this).attr('src'));
							})
							var $newElems = $(html).css({ opacity: 0}).appendTo(container);
							$newElems.imagesLoaded(function(){
								$newElems.animate({ opacity: 1},800);
								container.masonry( 'appended', $newElems,true);
								loading.data("on",true).fadeOut();
								clearTimeout(time);
					        });
						},800)
					}
				})(sqlJson);
			
				}
			
		});
		function loadImage(url) {
		     var img = new Image(); 
		     //创建一个Image对象，实现图片的预下载
		      img.src = url;
		      if (img.complete) {
		         return img.src;
		      }
		      img.onload = function () {
		       	return img.src;
		      };
		 };
		 loadImage('/media/images/btn_configuration.png');
		/*item hover效果*/
	/* 	var rbgB=['#71D3F5','#F0C179','#F28386','#8BD38B'];
		$('#showDepEmploy').on('mouseover','.item',function(){
			var random=Math.floor(Math.random() * 4);
			$(this).stop(true).animate({'backgroundColor':rbgB[random]},1000);
		});
		$('#showDepEmploy').on('mouseout','.item',function(){
			$(this).stop(true).animate({'backgroundColor':'#fff'},1000);
		}); */

  })
  </script> 
</head>
<body>
  <%@include file="/common/head.jsp"%>
  
<form action="findSrpingHello.do" method="post" onsubmit="return false;">
<input type="hidden"  name="showDataLineCount"  id="showDataLineCountID" value="${map.showDataLineCount eq 0 ? 20 :showDataLineCount}">

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

<div class="clearfloat showDepEmploy"  id="showDepEmploy">
    <%-- div class="panel-heading text-center">公司人员信息查询</div>--%>
    
    <ul>
    <c:forEach items="${employeeList}" var="employee" varStatus="index">
                <c:if test="${index.count le 8}">
					<li class="item">
						<h2 class="li-title" title="${employee.employeeName }">名字:${employee.employeeName }</h2>
						<p class="description">${employee.genderCodeValue }</p>
						<div class="qianm clearfloat">
							<span>${employee.workDepName }</span>
						</div>
					</li>
				</c:if>
               </c:forEach>
	</ul>
    
    <%-- <table class="table showDepEmploy">
           <thead>
                <tr>
                    <td >名字</td>
                    <td >性别</td>
                    <td >部门名称</td>
                </tr>
           </thead>
           <tbody>
             <c:forEach items="${employeeList}" var="employee" varStatus="index">
                <c:if test="${index.count le 20}">
	               <tr class="item">
	                      <td class="text-left">${employee.employeeName }</td>
	                      <td class="text-left">${employee.genderCodeValue }</td>
	                      <td class="text-left">${employee.workDepName }</td>
	               </tr>
                </c:if>
               </c:forEach>
           </tbody>
    </table> --%>
    
    
    
    
    <%--
    <display:table name="employeeList" id="row" pagesize="15" export="true" class="table text-left"  requestURI="findSrpingHello.do" >
	   <display:column property="employeeName" title="名字" sortable="true" headerClass="sortable"  value="${row.employeeName }"/>
	   <display:column property="genderCodeValue" title="性别" sortable="true" headerClass="sortable"  value="${row.genderCodeValue }"/>
	   <display:column property="workDepName" title="部门名称" sortable="true" headerClass="sortable"  value="${row.workDepName }"/>
	   <display:setProperty name="export.csv.filename" value="StaffInfo.csv"/>
	   <display:setProperty name="export.excel.filename" value="StaffInfo.xls"/>
   </display:table>
    --%>
</div>
	
	
<!-- loading按钮自己通过样式调整 -->
		<div id="imloading" style="width:150px;height:30px;line-height:30px;font-size:16px;text-align:center;border-radius:3px;opacity:0.7;background:#000;margin:10px auto 30px;color:#fff;display:none">
	       人员数据加载中.....
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