<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/Chart.js" />"></script>
<script src="<c:url value="/resources/js/Line.js" />"></script>
</head>
<body>
	<div style="float: left; margin-right: 3px">
		<form action="main-window">
			<input type=submit class="btnLogin width-135px" value="Main window"
				onClick="main-window">
		</form>
	</div>

	<div style="float: left; margin-right: 3px">
		<form action="chart-outside">
			<input type=submit class="btnLogin width-135px" value="Chart outside"
				onClick="chart-outside">
		</form>
	</div>
	
	<div style="float: left; margin-left: 35px; margin-top: 17px">
		This are charts of the temperatures in the flat.  
	</div>
	
	<div style="float: left; margin-right: 3px">
		
	</div>


	<div style="width: 100%">
		<div>
			<canvas id="canvas" height="240" width="1000"></canvas>
		</div>
	</div>


	<div style="float: left; margin-right: 3px">
		<form action="chart-inside-prev-hour">
			<input type=submit class="btnLogin width-135px" value="Backward"
				onClick="chart-inside-prev-hour">
		</form>
	</div>
	<div style="float: left; margin-right: 3px">
		<form action="chart-inside-next-hour">
			<input type=submit class="btnLogin width-135px" value="Forward"
				onClick="chart-inside-next-hour">
		</form>
	</div>


	<div style="float: left; margin-left: 35px; margin-top: 17px">
		Current hour is <c:out value="${currentHour}" />:00
	</div>
	
	<div style="float: left; margin-right: 3px">
		
	</div>

	<div style="width: 100%">
		<div>
			<canvas id="canvas1" height="240" width="1000"></canvas>
		</div>
	</div>


	<div style="float: left; margin-right: 3px">
		<form action="chart-inside-prev-day">
			<input type=submit class="btnLogin width-135px" value="Backward"
				onClick="chart-inside-prev-day">
		</form>
	</div>
	<div style="float: left; margin-right: 3px">
		<form action="chart-inside-next-day">
			<input type=submit class="btnLogin width-135px" value="Forward" onClick="chart-inside-next-day">
		</form>
	</div>
	
	<div style="float: left; margin-left: 35px; margin-top: 17px">
		Current day is <c:out value="${currentDate}" />
	</div>
	
	<div style="float: left; margin-right: 3px">
		
	</div>

	<script>
		var balcony_1 = new Array();
		var balcony_2 = new Array();
		var hall = new Array();
		var childRoom = new Array();
		var kitchen = new Array();
		var badRoom = new Array();
		var hallWay = new Array();
		var pantry = new Array();

		<c:forEach items ="${hour}" var = "inside" varStatus="status" >
		balcony_1.push("${inside.balcony_1}");
		balcony_2.push("${inside.balcony_2}");
		hall.push("${inside.hall}");
		childRoom.push("${inside.childRoom}");
		kitchen.push("${inside.kitchen}");
		badRoom.push("${inside.badRoom}");
		hallWay.push("${inside.hallWay}");
		pantry.push("${inside.pantry}");
		</c:forEach>
		
		Chart.defaults.global.animation = false;
		
		var lineChartData = {
			labels : [ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
					17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31,
					32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46,
					47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59 ],
			datasets : [ {
				label : "Blacony #1", /* red color */
				fillColor : "rgba(250,0,0,0.2)",
				strokeColor : "rgba(250,0,0,1)",
				pointColor : "rgba(250,0,0,1)",
				pointStrokeColor : "#fff",
				pointHighlightFill : "#fff",
				pointHighlightStroke : "rgba250,0,0,1)",
				data : balcony_1
			}, {
				label : "Balcony #2", /* dark green color */
				fillColor : "rgba(0,128,0,0.2)",
				strokeColor : "rgba(0,128,0,1)",
				pointColor : "rgba(0,128,0,1)",
				pointStrokeColor : "#fff",
				pointHighlightFill : "#fff",
				pointHighlightStroke : "rgba(0,128,0,1)",
				data : balcony_2
			}, {
				label : "Childroom", /* yellow color */
				fillColor : "rgba(255,255,0,0.2)",
				strokeColor : "rgba(255,255,0,1)",
				pointColor : "rgba(255,255,0,1)",
				pointStrokeColor : "#fff",
				pointHighlightFill : "#fff",
				pointHighlightStroke : "rgba(255,255,0,1)",
				data : childRoom
			}, {
				label : "Hall", /* purple color */
				fillColor : "rgba(0,128,0,0.2)",
				strokeColor : "rgba(0,128,0,1)",
				pointColor : "rgba(0,128,0,1)",
				pointStrokeColor : "#fff",
				pointHighlightFill : "#fff",
				pointHighlightStroke : "rgba(0,128,0,1)",
				data : hall
			}, {
				label : "Kitchen", /*black color*/
				fillColor : "rgba(0,0,0,0.2)",
				strokeColor : "rgba(0,0,0,1)",
				pointColor : "rgba(0,0,0,1)",
				pointStrokeColor : "#fff",
				pointHighlightFill : "#fff",
				pointHighlightStroke : "rgba(0,128,0,1)",
				data : kitchen
			}, {
				label : "Badroom", /* blue color */
				fillColor : "rgba(0,0,255,0.2)",
				strokeColor : "rgba(0,0,255,1)",
				pointColor : "rgba(0,0,255,1)",
				pointStrokeColor : "#fff",
				pointHighlightFill : "#fff",
				pointHighlightStroke : "rgba(0,0,255,1)",
				data : badRoom
			}, {
				label : "Hall way", /* olive color */
				fillColor : "rgba(128,128,0,0.2)",
				strokeColor : "rgba(128,128,0,1)",
				pointColor : "rgba(128,128,0,1)",
				pointStrokeColor : "#fff",
				pointHighlightFill : "#fff",
				pointHighlightStroke : "rgba(128,128,0,1)",
				data : hallWay
			}, {
				label : "Pantry", /* silver color */
				fillColor : "rgba(192,192,192,0.2)",
				strokeColor : "rgba(192,192,192,1)",
				pointColor : "rgba(192,192,192,1)",
				pointStrokeColor : "#fff",
				pointHighlightFill : "#fff",
				pointHighlightStroke : "rgba(192,192,192,1)",
				data : pantry
			} ]
		}
		
		var balcony_1Day = new Array();
		var balcony_2Day = new Array();
		var hallDay = new Array();
		var childRoomDay = new Array();
		var kitchenDay = new Array();
		var badRoomDay = new Array();
		var hallWayDay = new Array();
		var pantryDay = new Array();

		<c:forEach items ="${day}" var = "inside" varStatus="status" >
		balcony_1Day.push("${inside.balcony_1}");
		balcony_2Day.push("${inside.balcony_2}");
		hallDay.push("${inside.hall}");
		childRoomDay.push("${inside.childRoom}");
		kitchenDay.push("${inside.kitchen}");
		badRoomDay.push("${inside.badRoom}");
		hallWayDay.push("${inside.hallWay}");
		pantryDay.push("${inside.pantry}");
		</c:forEach>
		
		var lineChartData1 = {
				labels : [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 
				           11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23],
			datasets : [ {
				label : "Blacony #1", /* red color */
				fillColor : "rgba(250,0,0,0.2)",
				strokeColor : "rgba(250,0,0,1)",
				pointColor : "rgba(250,0,0,1)",
				pointStrokeColor : "#fff",
				pointHighlightFill : "#fff",
				pointHighlightStroke : "rgba250,0,0,1)",
				data : balcony_1Day
			}, {
				label : "Balcony #2", /* dark green color */
				fillColor : "rgba(0,128,0,0.2)",
				strokeColor : "rgba(0,128,0,1)",
				pointColor : "rgba(0,128,0,1)",
				pointStrokeColor : "#fff",
				pointHighlightFill : "#fff",
				pointHighlightStroke : "rgba(0,128,0,1)",
				data : balcony_2Day
			}, {
				label : "Childroom", /* yellow color */
				fillColor : "rgba(255,255,0,0.2)",
				strokeColor : "rgba(255,255,0,1)",
				pointColor : "rgba(255,255,0,1)",
				pointStrokeColor : "#fff",
				pointHighlightFill : "#fff",
				pointHighlightStroke : "rgba(255,255,0,1)",
				data : childRoomDay
			}, {
				label : "Hall", /* purple color */
				fillColor : "rgba(0,128,0,0.2)",
				strokeColor : "rgba(0,128,0,1)",
				pointColor : "rgba(0,128,0,1)",
				pointStrokeColor : "#fff",
				pointHighlightFill : "#fff",
				pointHighlightStroke : "rgba(0,128,0,1)",
				data : hallDay
			}, {
				label : "Kitchen", /*black color*/
				fillColor : "rgba(0,0,0,0.2)",
				strokeColor : "rgba(0,0,0,1)",
				pointColor : "rgba(0,0,0,1)",
				pointStrokeColor : "#fff",
				pointHighlightFill : "#fff",
				pointHighlightStroke : "rgba(0,128,0,1)",
				data : kitchenDay
			}, {
				label : "Badroom", /* blue color */
				fillColor : "rgba(0,0,255,0.2)",
				strokeColor : "rgba(0,0,255,1)",
				pointColor : "rgba(0,0,255,1)",
				pointStrokeColor : "#fff",
				pointHighlightFill : "#fff",
				pointHighlightStroke : "rgba(0,0,255,1)",
				data : badRoomDay
			}, {
				label : "Hall way", /* olive color */
				fillColor : "rgba(128,128,0,0.2)",
				strokeColor : "rgba(128,128,0,1)",
				pointColor : "rgba(128,128,0,1)",
				pointStrokeColor : "#fff",
				pointHighlightFill : "#fff",
				pointHighlightStroke : "rgba(128,128,0,1)",
				data : hallWayDay
			}, {
				label : "Pantry", /* silver color */
				fillColor : "rgba(192,192,192,0.2)",
				strokeColor : "rgba(192,192,192,1)",
				pointColor : "rgba(192,192,192,1)",
				pointStrokeColor : "#fff",
				pointHighlightFill : "#fff",
				pointHighlightStroke : "rgba(192,192,192,1)",
				data : pantryDay
			} ]
		}

		window.onload = function() {
			var ctx = document.getElementById("canvas").getContext("2d");
			window.myLine = new Chart(ctx).Line(lineChartData, {
				responsive : true,
				datasetFill : false,
				scaleOverride: true,
				 scaleStartValue: -25,
				 scaleStepWidth: 5,
				 scaleSteps: 16
			});
			var ctx1 = document.getElementById("canvas1").getContext("2d");
			window.myLine = new Chart(ctx1).Line(lineChartData1, {
				responsive : true,
				datasetFill : false,
				scaleOverride: true,
				 scaleStartValue: -25,
				 scaleStepWidth: 5,
				 scaleSteps: 16
			});
		}
	</script>



</body>


</html>