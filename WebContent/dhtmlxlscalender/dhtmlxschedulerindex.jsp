<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
  <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
  <title>日历</title>
  

   <link rel="stylesheet" href="../media/dhtmlxScheduler/dhtmlxscheduler.css" type="text/css" media="screen" title="no title" charset="utf-8">

  <!-- 
  <link rel="stylesheet" href="../media/dhtmlxScheduler/dhtmlxscheduler.css" type="text/css" title="no title" charset="utf-8">
   -->
  
  
  <link rel="stylesheet" href="../../media/bootstrap/css/bootstrap.css">
  <link rel="stylesheet" href="../../media/bootstrap/css/bootstrap-theme.css">
  <%@include file="/common/taglib.jsp"%>
  
  <style type="text/css" media="screen">
	html, body{
		margin:0px;
		padding:0px;
		height:100%;
		overflow:hidden;
	}	
</style>

 <script src="../media/dhtmlxScheduler/dhtmlxscheduler.js" type="text/javascript" charset="utf-8"></script>
 <script src="../media/dhtmlxScheduler/ext/dhtmlxscheduler_minical.js" type="text/javascript" charset="utf-8"></script>
 <script type="text/javascript">
 function init() {
		scheduler.config.multi_day = true;
		
		scheduler.config.xml_date="%Y-%m-%d %H:%i";
		scheduler.init('scheduler_here',new Date(2017,8,8),"week");
	//	scheduler.load("../dhtmlxlscalender/data/events.xml");
	}
	
	function show_minical(){
		if (scheduler.isCalendarVisible())
			scheduler.destroyCalendar();
		else
			scheduler.renderCalendar({
				position:"dhx_minical_icon",
				date:scheduler._date,
				navigation:true,
				handler:function(date,calendar){
					scheduler.setCurrentView(date);
					scheduler.destroyCalendar()
				}
			});
	}
 </script>
</head>
<body onload="init();">
	 <div id="scheduler_here" class="dhx_cal_container" style='width:100%; height:100%;'>
      <div class="dhx_cal_navline">
         <div class="dhx_cal_prev_button">&nbsp;</div>
         <div class="dhx_cal_next_button">&nbsp;</div>
         <div class="dhx_cal_today_button"></div>
         <div class="dhx_cal_date"></div>
         <div class="dhx_minical_icon" id="dhx_minical_icon" onclick="show_minical()">&nbsp;</div>
         <!-- <div class="dhx_cal_tab" name="day_tab" style="right:204px;"></div> 
         <!-- <div class="dhx_cal_tab" name="week_tab" style="right:140px;"></div>-->
         <!-- <div class="dhx_cal_tab" name="month_tab" style="right:76px;"></div> -->
      </div>
      <div class="dhx_cal_header">
      </div>
      <div class="dhx_cal_data">
      </div>
   </div>


</body>
</html>